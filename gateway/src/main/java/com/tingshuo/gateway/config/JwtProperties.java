package com.tingshuo.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * packageName com.tingshuo.gateway.config
 *
 * @author tingshuo
 * @version JDK 8
 * @className JwtProperties
 * @date 2026/1/2 17:35
 * @description 类描述信息 配置类
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    /**
     * 密钥
     */
    private String secret;
    /**
     * 令牌前缀
     */
    private Long expiration =3600000L;
    /**
     * 刷新令牌前缀
     */
    private String header;
    /**
     * 令牌前缀
     */
    private String tokenPrefix;


}

