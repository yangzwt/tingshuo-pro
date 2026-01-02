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
 * @className SysUserEntity
 * @date 2026/1/1 18:19
 * @description 类描述信息 系统用户
 */
@Data
@TableName("sys_user")
public class SysUserEntity {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String nickname;
    private String email;
    private Long deptId;
    private Integer status; // 1:正常 0:禁用
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
