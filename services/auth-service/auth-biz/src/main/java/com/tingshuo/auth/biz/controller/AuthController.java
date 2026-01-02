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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private SysUserService userService;

    @Autowired
    private SysMenuService menuService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO request) {
        SysUserEntity user = userService.findByUsername(request.getUsername());
        if (user == null || !userService.matchesPassword(request.getPassword(), user.getPassword())) {
            return Result.fail("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());

        String token = jwtUtil.generateToken(claims, user.getUsername());
        return Result.success(token);
    }

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
