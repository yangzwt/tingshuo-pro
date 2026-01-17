package com.tingshuo.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.coupon.entity.UserCouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tingshuo
 * @version JDK 8
 * @className UserCouponMapper
 * @date 2026/1/17 18:33
 * @description 接口描述信息 用户优惠券接口
 */
@Mapper
public interface UserCouponMapper extends BaseMapper<UserCouponEntity> {
}
