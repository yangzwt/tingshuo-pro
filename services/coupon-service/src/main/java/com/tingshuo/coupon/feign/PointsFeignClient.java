package com.tingshuo.coupon.feign;

import com.tingshuo.common.core.web.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsFeignClient
 * @date 2026/1/24 9:45
 * @description 接口描述信息 积分服务Feign客户端
 */
@FeignClient(name = "tingshuo-points")
public interface PointsFeignClient {
    /**
     * 添加积分
     * @param userId 用户ID
     * @param points 积分数
     * @param reason 积分原因
     * @return
     */
    @PostMapping("/api/points/add")
    Result<Boolean> addPoints(@RequestParam("userId") Long userId,
                              @RequestParam("points") Integer points,
                              @RequestParam("reason") String reason);

}
