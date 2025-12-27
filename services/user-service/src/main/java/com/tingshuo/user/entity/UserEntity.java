package com.tingshuo.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * packageName com.tingshuo.user
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserEntity
 * @date 2025/12/27 15:56
 * @description 类描述信息
 */
@Data
@TableName("tingshuo_user")
public class UserEntity {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 余额
     */
    private BigDecimal balance;
}
