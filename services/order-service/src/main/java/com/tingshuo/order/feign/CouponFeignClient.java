package com.tingshuo.order.feign;

import com.tingshuo.common.core.web.Result;
import com.tingshuo.order.dto.UseCouponDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponClient
 * @date 2026/1/17 18:53
 * @description 接口描述信息 优惠券服务客户端
 */
@FeignClient(name = "coupon-service")
public interface CouponFeignClient {

        @PostMapping("/api/coupon/internal/use")
        Result<Boolean> useCoupon(@RequestBody UseCouponDTO request);

        @GetMapping("/api/coupon/internal/validate")
        Result<Boolean> validateCoupon(@RequestParam("userId") Long userId,
                                       @RequestParam("couponCode") String couponCode);
}
