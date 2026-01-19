package com.tingshuo.auth.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tingshuo
 * @version JDK 8
 * @className SysUserRoleEntity
 * @date 2026/1/19 21:12
 * @description 类描述信息 用户角色关系表
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
