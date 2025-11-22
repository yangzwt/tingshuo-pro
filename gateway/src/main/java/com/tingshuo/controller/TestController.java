package com.tingshuo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * packageName com.tingshuo.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className TestController (此处以class为例)
 * @date 2025/11/8-18:55
 * @description TODO
 */
@RestController
@RequestMapping("/test")
@RefreshScope // nacos 配置动态刷新
public class TestController {
    Logger log = LoggerFactory.getLogger(TestController.class);
    // nacos配置,增加默认值
    @Value("${nacos.key:00}")
    private String nacosKey;
    /**
     * 测试接口
     * @return
     */
    @GetMapping("/test")
    public String test(){
        log.info("测试接口");
        return nacosKey;
    }
}
