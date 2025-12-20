package com.tingshuo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * packageName com.tingshuo.product
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductApplication (此处以class为例)
 * @date 2025/12/20-18:05
 * @description 类描述信息 商品服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
