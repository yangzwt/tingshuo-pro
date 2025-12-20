package com.tingshuo.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * packageName com.tingshuo.storage
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageApplication (此处以class为例)
 * @date 2025/12/20-17:34
 * @description 类描述信息 库存模块启动
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
