package com.tingshuo.auth.biz.service.impl;

import com.tingshuo.auth.biz.dao.SysUserMapper;
import com.tingshuo.auth.biz.entity.SysUserEntity;
import com.tingshuo.auth.biz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName com.tingshuo.auth.biz.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysUserServiceImpl
 * @date 2026/1/2 15:35
 * @description 类描述信息 用户服务实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUserEntity findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        // 方案1: 开发阶段 - 明文比对（根据你的种子数据）
        if (encodedPassword.equals(rawPassword)) {
            return true;
        }

        // 方案2: 生产阶段 - BCrypt（取消注释下面）
        /*
        if (passwordEncoder != null) {
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
        */

        return false;
    }

    @Override
    public SysUserEntity findById(Long userId) {
        if (userId == null){
            return null;
        }
        return sysUserMapper.selectById(userId);
    }
}
