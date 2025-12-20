package com.tingshuo.asset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

/**
 * packageName com.tingshuo.asset
 * 描述 资产服务启动类
 * @author tingshuo
 * @version JDK 8
 * @className AssetApplication (此处以class为例)
 * @date 2025/11/29-14:32
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AssetApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssetApplication.class,args);
    }

}
