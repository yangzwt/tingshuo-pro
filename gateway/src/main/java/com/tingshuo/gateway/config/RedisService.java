package com.tingshuo.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * packageName com.tingshuo.gateway.config
 *
 * @author tingshuo
 * @version JDK 8
 * @className RedisService
 * @date 2026/1/10 14:52
 * @description 类描述信息 redis服务
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     *  设置缓存
     * @param key 缓存key
     * @param value 缓存值
     * @param expireTime 缓存时间
     */
    public void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.MILLISECONDS);
    }
    /**
     *  获取缓存
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     *  删除缓存
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
