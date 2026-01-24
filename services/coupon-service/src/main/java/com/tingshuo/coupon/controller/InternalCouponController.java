package com.tingshuo.coupon.controller;

import com.tingshuo.common.core.web.Result;
import com.tingshuo.coupon.dto.UseCouponDTO;
import com.tingshuo.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tingshuo
 * @version JDK 8
 * @className InternalCouponController
 * @date 2026/1/17 18:50
 * @description 类描述信息 内部优惠券服务
 */
@RestController
@RequestMapping("/api/coupon/internal")
public class InternalCouponController {
    @Autowired
    private CouponService couponService;

    /**
     * 订单服务调用：核销优惠券
     * @param request
     * @return
     */
    @PostMapping("/use")
    public Result<Boolean> useCoupon(@RequestBody UseCouponDTO request) {
        boolean success = couponService.useCoupon(request.getUserId(), request.getCouponCode());
        return Result.success(success);
    }

    /**
     * 验证优惠券是否有效（下单前校验）
     * @param userId
     * @param couponCode
     * @return
     */
    @GetMapping("/validate")
    public Result<Boolean> validate(@RequestParam Long userId,
                                    @RequestParam String couponCode) {
        // 可扩展：返回可用金额、类型等
        boolean valid = couponService.getUserAvailableCoupons(userId)
                .stream()
                .anyMatch(uc -> couponCode.equals(uc.getCode()));
        return Result.success(valid);
    }
}
