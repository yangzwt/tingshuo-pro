package com.tingshuo.asset.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述 : 远程调用用户服务
 * @author tingshuo
 * @version 1.0.0
 * @date 2025/11/29 14:39
 */
@FeignClient(name = "tingshuo-user")
public interface UserClientAPI {
    @GetMapping("/user/getUserById/{userId}")
    String getUserById(@PathVariable("userId") String userId);
}
