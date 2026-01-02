package com.tingshuo.auth.biz.service.impl;

import com.tingshuo.auth.biz.dao.SysMenuMapper;
import com.tingshuo.auth.biz.entity.SysMenuEntity;
import com.tingshuo.auth.biz.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName com.tingshuo.auth.biz.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysMenuServiceImpl
 * @date 2026/1/2 15:38
 * @description 类描述信息
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public List<SysMenuEntity> getMenusByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            return java.util.Collections.emptyList();
        }
        return menuMapper.selectMenusByUserId(userId);
    }
}
