package com.tingshuo.auth.biz.controller;

import com.tingshuo.auth.api.dto.LoginDTO;
import com.tingshuo.auth.api.vo.MenuVO;
import com.tingshuo.auth.api.vo.UserVO;
import com.tingshuo.auth.biz.entity.SysMenuEntity;
import com.tingshuo.auth.biz.entity.SysUserEntity;
import com.tingshuo.auth.biz.service.SysMenuService;
import com.tingshuo.auth.biz.service.SysUserService;
import com.tingshuo.common.core.security.JwtUtil;
import com.tingshuo.common.core.web.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * packageName com.tingshuo.auth.biz.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className AuthController
 * @date 2026/1/2 15:40
 * @description 类描述信息
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysMenuService menuService;
    private static final String BLACKLIST_PREFIX = "jwt:blacklist:";
    private static final String TOKEN_PREFIX = "auth:token:";
    private static final String USER_JTIS_PREFIX = "auth:user:";

    /**
     *  登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO request) {
        SysUserEntity user = userService.findByUsername(request.getUsername());
        if (user == null || !userService.matchesPassword(request.getPassword(), user.getPassword())) {
            return Result.fail("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        // 生成令牌
        String token = jwtUtil.generateToken(claims, user.getUsername());
        //System.out.println("【AUTH】生成的 Token = " + token);

        //新增：存入 Redis 白名单和用户会话 ===
        String jtiFromToken = jwtUtil.getJtiFromToken(token);
        long ttlSeconds = jwtUtil.getExpiration() / 1000; // 假设 expiration 是毫秒
        // 1. 白名单：token -> userId（用于快速校验）
        redisTemplate.opsForValue().set(
                "auth:token:" + jtiFromToken,
                user.getUserId().toString(),
                ttlSeconds,
                TimeUnit.SECONDS
        );
        // 2. 用户会话集合：userId -> Set<jti>（用于“登出所有设备”）
        redisTemplate.opsForSet().add(
                TOKEN_PREFIX + user.getUserId().toString(),
                jtiFromToken
        );
        // 设置集合过期时间（可选，防止长期残留）
        redisTemplate.expire("auth:user:" + user.getUserId().toString(), ttlSeconds, TimeUnit.SECONDS);
        //String header = new String(java.util.Base64.getUrlDecoder().decode(token.split("\\.")[0]));
        //System.out.println("【AUTH】Token Header: " + header);
        return Result.success(token);
    }

    /**
     *  登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.success("Logged out");
        }
        String token = authHeader.substring(7);
        try {
            // 解析令牌
            Claims claims = jwtUtil.parseClaims(token);
            if (claims == null) {
                return Result.success("Logged out");
            }
            // 获取 jti
            String jti = claims.getId();
            Object userIdObj = claims.get("userId");
            if (jti == null || userIdObj == null) {
                return Result.success("Logged out");
            }
            String userId = userIdObj.toString();

            // 1. 计算黑名单 TTL（至少保留 5 分钟）
            Date expiration = claims.getExpiration();
            long ttlMillis = expiration != null
                    ? Math.max(expiration.getTime() - System.currentTimeMillis(), 5 * 60 * 1000)
                    : 5 * 60 * 1000;

            // 2. 加入黑名单（关键！）
            redisTemplate.opsForValue().set(
                    BLACKLIST_PREFIX + jti,
                    "logged_out",
                    ttlMillis,
                    TimeUnit.MILLISECONDS
            );

            // 3. 清理白名单（如果使用）
            redisTemplate.delete(TOKEN_PREFIX + jti);

            // 4. 从用户会话中移除（或直接删除全部）
            // 方案 A：仅登出当前设备
            redisTemplate.opsForSet().remove(USER_JTIS_PREFIX + userId, jti);

            // 方案 B：登出所有设备（取消注释下面这行，注释上面那行）
            // redisTemplate.delete(USER_JTIS_PREFIX + userId);

            return Result.success("Logged out successfully");

        } catch (Exception e) {
            // 无效 token 视为已登出（幂等）
            return Result.success("Logged out");
        }
    }

    /**
     * 获取用户信息
     * 直接获取请求头的令牌方式
     * @param request
     * @return
     */
    @GetMapping("/user/info")
    public Result<UserVO> getUserInfo(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.fail("缺少有效令牌");
        }

        String token = authHeader.substring(7); // 去掉 "Bearer "

        if (!jwtUtil.validateToken(token)) {
            return Result.fail("无效令牌");
        }
        String username = jwtUtil.getUsernameFromToken(token);
        SysUserEntity user = userService.findByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return Result.success(vo);
    }

    @GetMapping("/user/infoNew")
    public Result<UserVO> getUserInfoNew(@RequestHeader("X-User-Id") Long userId) {

        SysUserEntity user = userService.findById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return Result.success(vo);
    }
    @GetMapping("/menu/list")
    public Result<List<MenuVO>> getMenuList(@RequestParam("userId") Long userId) {
        List<SysMenuEntity> menus = menuService.getMenusByUserId(userId);
        List<MenuVO> menuVOs = menus.stream().map(menu -> {
            MenuVO vo = new MenuVO();
            BeanUtils.copyProperties(menu, vo);
            return vo;
        }).collect(Collectors.toList());
        return Result.success(menuVOs);
    }
}
