package com.tingshuo.order.dto;

import lombok.Data;

/**
 * @author tingshuo
 * @version JDK 8
 * @className UseCouponDTO
 * @date 2026/1/17 18:55
 * @description 类描述信息 优惠券使用DTO
 */
@Data
public class UseCouponDTO {
    private Long userId;
    private String couponCode;
}
