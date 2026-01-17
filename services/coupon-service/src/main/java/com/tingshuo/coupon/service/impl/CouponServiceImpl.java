package com.tingshuo.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tingshuo.coupon.dao.CouponMapper;
import com.tingshuo.coupon.dao.UserCouponMapper;
import com.tingshuo.coupon.dto.CreateCouponDTO;
import com.tingshuo.coupon.entity.CouponEntity;
import com.tingshuo.coupon.entity.UserCouponEntity;
import com.tingshuo.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tingshuo
 * @version JDK 8
 * @className CouponServiceImpl
 * @date 2026/1/17 18:39
 * @description 类描述信息 优惠券服务实现类
 */
@Slf4j
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;
    /**
     * 创建优惠券
     * @param dto
     */
    @Override
    public void createCoupon(CreateCouponDTO dto) {
        CouponEntity coupon = new CouponEntity();
        BeanUtils.copyProperties(dto, coupon);
        coupon.setDeleted(false);
        coupon.setStatus(0);
        coupon.setCreateTime(LocalDateTime.now());
        coupon.setUpdateTime(LocalDateTime.now());
        couponMapper.insert(coupon);
    }

    /**
     *  获取用户可用优惠券
     * @param userId
     * @return
     */
    @Override
    public List<UserCouponEntity> getUserAvailableCoupons(Long userId) {
        QueryWrapper<UserCouponEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("status", 0)
                .eq("deleted", false);
        // 查询用户优惠券
        List<UserCouponEntity> list = userCouponMapper.selectList(wrapper);

        return list.stream().filter(uc -> {
            CouponEntity coupon = couponMapper.selectById(uc.getCouponId());
            if (coupon == null || coupon.getDeleted()) return false;
            LocalDateTime now = LocalDateTime.now();
            return !now.isBefore(coupon.getValidStart()) && !now.isAfter(coupon.getValidEnd());
        }).collect(Collectors.toList());
    }

    /**
     *  使用优惠券
     * @param userId
     * @param couponCode
     * @return
     */
    @Override
    public boolean useCoupon(Long userId, String couponCode) {
        // 1. 查用户券记录（未使用）
        UserCouponEntity userCoupon = userCouponMapper.selectOne(
                new QueryWrapper<UserCouponEntity>()
                        .eq("user_id", userId)
                        .eq("code", couponCode)
                        .eq("status", 0)
                        .eq("deleted", false)
        );

        if (userCoupon == null) {
            log.warn("用户 {} 无可用券: {}", userId, couponCode);
            return false;
        }

        // 2. 查模板是否有效
        CouponEntity coupon = couponMapper.selectById(userCoupon.getCouponId());
        if (coupon == null || coupon.getDeleted()) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getValidStart()) || now.isAfter(coupon.getValidEnd())) {
            return false;
        }

        // 3. 更新用户券为已使用
        userCoupon.setStatus(1);
        userCoupon.setUsedTime(now);
        userCoupon.setUpdateTime(now);
        userCouponMapper.updateById(userCoupon);

        return true;
    }
}
