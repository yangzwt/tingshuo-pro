package com.tingshuo.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponApplication
 * @date 2026/1/17 18:21
 * @description 类描述信息 优惠券服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }
}
