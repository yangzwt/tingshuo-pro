package com.tingshuo.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponMapper
 * @date 2026/1/17 18:32
 * @description 接口描述信息 优惠券数据接口
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponEntity> {
}
