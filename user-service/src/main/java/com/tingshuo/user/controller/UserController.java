package com.tingshuo.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.user.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserController (此处以class为例)
 * @date 2025/11/29-12:24
 * @description TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserById/{userId}")
    public String getUserById(@PathVariable("userId") String userId){
        return "tingshuo-user->"+userId;
    }
}
