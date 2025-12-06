package com.tingshuo.asset.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tingshuo.asset.api.UserClientAPI;
import com.tingshuo.asset.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @SentinelResource(
            value = "getUserById",
            fallback = "getUserByIdFallback",        // 处理业务异常（如 FeignException）
            blockHandler = "getUserByIdBlock"        // 处理 Sentinel 熔断/限流
    )
    public UserDTO getUserById(@PathVariable("userId") String userId) {
        return userClientAPI.getUserById(userId); // 可能抛 FeignException
    }

    // 1. 业务异常 fallback（如 userservice 抛异常、网络超时等）
    public UserDTO getUserByIdFallback(String userId, Throwable ex) {
        System.out.println("【Fallback】业务异常: " + ex.getClass().getSimpleName());
        return new UserDTO(userId, "【业务异常】用户服务调用失败: " + ex.getMessage());
    }

    // 2. Sentinel block handler（熔断或限流触发）
    public UserDTO getUserByIdBlock(String userId, BlockException ex) {
        System.out.println("【BlockHandler】被 Sentinel 熔断/限流");

        return new UserDTO(userId, "【Sentinel 熔断】用户服务不可用，请稍后再试");
    }
}
