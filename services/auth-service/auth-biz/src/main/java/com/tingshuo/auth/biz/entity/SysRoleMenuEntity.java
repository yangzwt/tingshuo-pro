package com.tingshuo.auth.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tingshuo
 * @version JDK 8
 * @className SysRoleMenuEntity
 * @date 2026/1/19 21:10
 * @description 类描述信息 角色菜单关系表
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity {
    /**
     *  角色ID
     */
    private Long roleId;
    /**
     *  菜单ID
     */
    private Long menuId;
}
