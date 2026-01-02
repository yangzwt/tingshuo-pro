package com.tingshuo.auth.biz.service;

import com.tingshuo.auth.biz.entity.SysMenuEntity;

import java.util.List;

/**
 * packageName com.tingshuo.auth.biz.service
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysMenuService
 * @date 2026/1/2 15:37
 * @description 接口描述信息 菜单服务
 */
public interface SysMenuService {
    List<SysMenuEntity> getMenusByUserId(Long userId);
}
