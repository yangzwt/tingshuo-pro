package com.tingshuo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className TestController (此处以class为例)
 * @date 2025/11/8-18:55
 * @description TODO
 */
@RestController
public class TestController {
    /**
     * 测试接口
     * @return
     */
    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
}
