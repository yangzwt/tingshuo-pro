package com.tingshuo.auth.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName com.tingshuo.auth.entity
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysRoleEntity
 * @date 2026/1/1 18:20
 * @description 类描述信息 系统角色
 */
@Data
@TableName("sys_role")
public class SysRoleEntity {
    @TableId(type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleCode;
    private String description;
    private Integer status;
    private LocalDateTime createTime;
}
