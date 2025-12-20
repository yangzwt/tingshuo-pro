package com.tingshuo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * packageName com.tingshuo.order
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderApplication (此处以class为例)
 * @date 2025/12/20-16:05
 * @description 类描述信息 订单服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
