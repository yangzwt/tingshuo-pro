package com.tingshuo.coupon.dto;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CreateCouponDTO
 * @date 2026/1/17 18:34
 * @description 类描述信息 创建优惠券DTO
 */
@Data
@NoArgsConstructor // 👈 关键：生成无参构造函数
@AllArgsConstructor // 可选：生成全参构造函数
public class CreateCouponDTO {
    //@NotBlank
    private String couponCode;
    //@NotBlank
    private String name;
    @NotNull
    private Integer type;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private BigDecimal minAmount;
    @NotNull
    private LocalDateTime validStart;
    @NotNull
    private LocalDateTime validEnd;
}
