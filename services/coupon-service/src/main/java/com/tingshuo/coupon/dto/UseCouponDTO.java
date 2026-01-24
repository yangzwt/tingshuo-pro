package com.tingshuo.coupon.dto;

import lombok.Data;

/**
 * @author tingshuo
 * @version JDK 8
 * @className UseCouponDTO
 * @date 2026/1/17 18:36
 * @description 类描述信息 优惠券使用DTO
 */
@Data
public class UseCouponDTO {
    private Long userId;
    /**
     * 优惠券码
     */
    private String couponCode;
    /**
     * 积分变动值
     */
    private Integer points; // 积分变动值（+50）
}
