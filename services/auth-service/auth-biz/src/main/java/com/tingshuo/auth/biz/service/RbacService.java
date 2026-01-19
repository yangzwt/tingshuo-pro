package com.tingshuo.auth.biz.service;

import java.util.List;

/**
 * @author tingshuo
 * @version JDK 8
 * @className RbacService
 * @date 2026/1/19 21:04
 * @description 接口描述信息 权限相关业务接口
 */
public interface RbacService {
    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    List<String> selectPermissionsByUserId(Long userId);
}
