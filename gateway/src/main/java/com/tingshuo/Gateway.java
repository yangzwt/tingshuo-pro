package com.tingshuo;

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
 * @description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {
    public static void main(String[] args) {
        SpringApplication.run(Gateway.class,args);
    }
}
