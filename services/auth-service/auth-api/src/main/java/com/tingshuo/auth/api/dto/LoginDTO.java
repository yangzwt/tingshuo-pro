package com.tingshuo.auth.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.auth.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className LoginDTO
 * @date 2026/1/1 18:23
 * @description 类描述信息 登录参数 数据传输对象
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
    private String loginType; // WEB / APP
}
