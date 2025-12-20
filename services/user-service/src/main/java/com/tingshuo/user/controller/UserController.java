package com.tingshuo.user.controller;

import com.tingshuo.user.dto.UserDTO;
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
 * @description
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
    public UserDTO getUserById(@PathVariable("userId") String userId){
        try {
            Thread.sleep(800);
        }catch (Exception e){
            System.out.println(e);
        }

        if ("123456".equals(userId)){
            throw new RuntimeException("用户服务异常测试熔断");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setName("tingshuo-user->"+userId);
       return userDTO;
    }
}
