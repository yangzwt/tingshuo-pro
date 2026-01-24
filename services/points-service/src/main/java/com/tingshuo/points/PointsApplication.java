package com.tingshuo.points;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 类文件描述: 积分服务启动类
 * @author tingshuo
 * @version 1.0.0
 * @date 2026/01/24 09:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PointsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointsApplication.class, args);
    }
}
