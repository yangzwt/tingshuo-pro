package com.tingshuo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * packageName com.tingshuo
 *
 * @author tingshuo
 * @version JDK 8
 * @className Gateway (此处以class为例)
 * @date 2025/11/8-18:41
 * @description  类描述信息 网关启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
