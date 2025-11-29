package com.tingshuo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * packageName com.tingshuo.user
 * 用户服务启动类
 * @author tingshuo
 * @version JDK 8
 * @className UserApplication (此处以class为例)
 * @date 2025/11/29-12:19
 * @description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
