package com.tingshuo.auth.biz.service.impl;

import com.tingshuo.auth.biz.dao.SysMenuMapper;
import com.tingshuo.auth.biz.dao.SysRoleMenuMapper;
import com.tingshuo.auth.biz.dao.SysUserRoleMapper;
import com.tingshuo.auth.biz.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tingshuo
 * @version JDK 8
 * @className RbacServiceImpl
 * @date 2026/1/19 21:06
 * @description 类描述信息 角色权限服务实现类
 */
@Service
public class RbacServiceImpl implements RbacService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper; // 对应 sys_user_role
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper; // 新增：sys_role_menu
    @Autowired
    private SysMenuMapper sysMenuMapper;        // 对应 sys_menu
    @Override
    public List<String> selectPermissionsByUserId(Long userId) {
        // 1. 查用户的角色ID
        List<Long> roleIds = sysUserRoleMapper.selectRoleIdsByUserId(userId);
       if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 2. 查这些角色关联的菜单ID
        List<Long> menuIds = sysRoleMenuMapper.selectMenuIdsByRoleIds(roleIds);
        if (menuIds.isEmpty()) return Collections.emptyList();
        // 3. 查菜单的 permission 字段（去重、非空）
        return sysMenuMapper.selectPermissionsByIds(menuIds).stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}
