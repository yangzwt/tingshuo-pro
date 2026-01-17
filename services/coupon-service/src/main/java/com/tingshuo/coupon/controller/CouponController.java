package com.tingshuo.coupon.controller;

import com.tingshuo.common.core.web.Result;
import com.tingshuo.coupon.dto.CreateCouponDTO;
import com.tingshuo.coupon.entity.UserCouponEntity;
import com.tingshuo.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponController
 * @date 2026/1/17 18:44
 * @description 类描述信息 优惠券控制类
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;
    // 运营创建优惠券（可加权限控制）
    @PostMapping("/create")
    public Result<?> create(@RequestBody CreateCouponDTO dto) {
        couponService.createCoupon(dto);
        return Result.success("ok");
    }

    // 用户查看可用券
    @GetMapping("/list")
    public Result<List<UserCouponEntity>> list(@RequestHeader("X-User-ID") Long userId) {
        List<UserCouponEntity> coupons = couponService.getUserAvailableCoupons(userId);
        return Result.success(coupons);
    }

    // 用户手动使用券（一般由订单服务调用，此处仅作测试）
    @PostMapping("/use")
    public Result<Boolean> use(@RequestHeader("X-User-ID") Long userId,
                               @RequestParam String code) {
        boolean success = couponService.useCoupon(userId, code);
        return Result.success(success);
    }
}
