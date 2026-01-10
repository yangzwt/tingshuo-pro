package com.tingshuo.auth.biz.controller;

import com.tingshuo.common.core.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * packageName com.tingshuo.auth.biz.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className AdminController
 * @date 2026/1/10 15:34
 * @description 类描述信息
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String BLACKLIST_PREFIX = "jwt:blacklist:";
    private static final String USER_JTIS_PREFIX = "user:jtis:";

    /**
     * 强制下线指定用户（踢人）
     * URL: DELETE /admin/kick/user:1001
     */
//    @DeleteMapping("/kick/{userId}")
//    @PreAuthorize("hasRole('ADMIN')") // 需要 Spring Security 支持
//    public Result<?> kickUser(@PathVariable String userId) {
//        String userJtisKey = USER_JTIS_PREFIX + userId;
//        Set<String> jtis = redisTemplate.opsForSet().members(userJtisKey);
//
//        if (jtis != null && !jtis.isEmpty()) {
//            for (String jti : jtis) {
//                // 加入黑名单，统一设 1 小时兜底（避免永久占用内存）
//                redisTemplate.opsForValue().set(
//                        BLACKLIST_PREFIX + jti,
//                        "kicked_by_admin",
//                        3600,
//                        TimeUnit.SECONDS
//                );
//            }
//            // 清除该用户的 jti 记录
//            redisTemplate.delete(userJtisKey);
//        }
//        Map<String, String> resp = new HashMap<>();
//        resp.put("message", "User " + userId + " has been kicked");
//        return Result.success(resp);
//    }
}
