package com.tingshuo.auth.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.auth.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className LoginResult
 * @date 2026/1/1 18:24
 * @description 类描述信息 登录结果
 */
@Data
public class LoginResult {
    private String token;
    private UserDTO user;
}
