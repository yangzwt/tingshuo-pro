package com.tingshuo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * packageName com.tingshuo.order.feign
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserFeignClient
 * @date 2025/12/20-19:02
 * @description 接口描述信息 用户服务接口
 */
@FeignClient(name = "user-service")
public interface UserFeignClient {
    /**
     * 判断用户是否存在
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    boolean UserExists(@PathVariable("id") Long id);
}
