package com.tingshuo.coupon.service;

import com.tingshuo.coupon.dto.CreateCouponDTO;
import com.tingshuo.coupon.entity.UserCouponEntity;

import java.util.List;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponService
 * @date 2026/1/17 18:37
 * @description 接口描述信息 优惠券服务
 */
public interface CouponService {
    /**
     * 创建优惠券
     * @param dto
     */
    void createCoupon(CreateCouponDTO dto);
    /**
     * 获取用户可用优惠券
     * @param userId
     * @return
     */
    List<UserCouponEntity> getUserAvailableCoupons(Long userId);
    /**
     * 使用优惠券
     * @param userId
     * @param couponCode
     * @return
     */
    boolean useCoupon(Long userId, String couponCode);
}
