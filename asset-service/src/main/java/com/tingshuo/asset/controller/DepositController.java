package com.tingshuo.asset.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tingshuo.asset.api.UserClientAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.asset.controller
 * 描述 存款服务控制层
 * @author tingshuo
 * @version JDK 8
 * @className DepositController (此处以class为例)
 * @date 2025/11/29-14:38
 * @description TODO
 */
@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private UserClientAPI userClientAPI;
    /**
     * 存款调用用户服务，查询用户基本信息
     * @return
     */
    @GetMapping("/getUserById/{userId}")
    @SentinelResource(value = "getUserById",blockHandler = "getUserByIdFallback")
    public String getUserById(@PathVariable("userId") String userId) {
        return userClientAPI.getUserById(userId);
    }

    /**
     *  降级处理
     * @param userId
     * @param e
     * @return
     */
    public String getUserByIdFallback(String userId, BlockException e) {
        System.out.println(e.getMessage());
        System.out.println("进入数据限流操作相关");
        return "用户服务异常"+userId+"服务请求频繁";
    }
}
