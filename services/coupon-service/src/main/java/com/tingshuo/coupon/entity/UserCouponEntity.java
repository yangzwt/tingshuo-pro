package com.tingshuo.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tingshuo
 * @version JDK 8
 * @className UserCouponEntity
 * @date 2026/1/17 18:31
 * @description 类描述信息 用户优惠券
 */
@Data
@TableName("t_user_coupons")
public class UserCouponEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long couponId;
    private String code;            // 冗余 coupon_code
    private Integer status;         // 0=未使用, 1=已使用, 2=已过期
    private LocalDateTime usedTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
