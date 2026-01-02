package com.tingshuo.auth.biz.service;

import com.tingshuo.auth.biz.entity.SysUserEntity;

/**
 * packageName com.tingshuo.auth.biz.service
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysUserService
 * @date 2026/1/2 15:34
 * @description 接口描述信息 用户服务
 */
public interface SysUserService {

    SysUserEntity findByUsername(String username);

    boolean matchesPassword(String rawPassword, String encodedPassword);
}
