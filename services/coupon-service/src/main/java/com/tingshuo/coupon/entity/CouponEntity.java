package com.tingshuo.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponEntity
 * @date 2026/1/17 18:29
 * @description 类描述信息 优惠券
 */
@Data
@TableName("t_coupons")
public class CouponEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String couponCode;      // 优惠券码（唯一）
    private String name;            // 名称
    private Integer type;           // 0=满减, 1=折扣
    private BigDecimal amount;      // 减免金额 或 折扣比例（如 0.9 表示 9 折）
    private BigDecimal minAmount;   // 最低消费金额
    private LocalDateTime validStart;
    private LocalDateTime validEnd;
    private Integer status;         // 模板状态（通常不用，保留）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
