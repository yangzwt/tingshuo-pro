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
 * @className SysMenuEntity
 * @date 2026/1/1 18:21
 * @description 类描述信息 菜单实体类
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity {
    @TableId(type = IdType.AUTO)
    private Long menuId;
    private Long parentId;
    private String name;
    private String path;
    private String component;
    private String perms;
    private String icon;
    private Integer sort;
    private Boolean hidden;
    private String menuName;
    private Integer type;
    private String permission;
    private Integer status;
    private LocalDateTime createTime;
}
