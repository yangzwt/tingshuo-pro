//package com.tingshuo.asset.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * packageName com.tingshuo.asset.utils
// *
// * @author tingshuo
// * @version JDK 8
// * @className SentinelConfigPrinter (此处以class为例)
// * @date 2025/12/6-11:52
// * @description TODO
// */
//@Component
//public class SentinelConfigPrinter {
//    @Autowired
//    private Environment environment;
//
//    @PostConstruct
//    public void printSentinelConfig() {
//        String dashboard = environment.getProperty("spring.cloud.sentinel.transport.dashboard");
//        String port = environment.getProperty("spring.cloud.sentinel.transport.port");
//        String clientIp = environment.getProperty("spring.cloud.sentinel.transport.client-ip");
//
//        System.out.println("===================================");
//        System.out.println(">>> Sentinel Transport 配置检查:");
//        System.out.println(">>> dashboard = " + dashboard);
//        System.out.println(">>> port      = " + port);
//        System.out.println(">>> client-ip = " + clientIp);
//        System.out.println("===================================");
//    }
//}
