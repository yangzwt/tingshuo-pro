package com.tingshuo.auth.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.auth.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className RegisterUserDTO
 * @date 2026/1/1 19:48
 * @description 类描述信息 注册用户参数
 */
@Data
public class RegisterUserDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 新密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;
}
