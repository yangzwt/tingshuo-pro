package com.tingshuo.auth.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.auth.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserDTO
 * @date 2026/1/1 18:24
 * @description 类描述信息 用户信息
 */
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String phone;
}
