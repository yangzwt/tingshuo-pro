package com.tingshuo.auth.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * packageName com.tingshuo.auth.api.vo
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserVO
 * @date 2026/1/2 14:45
 * @description 类描述信息 用户信息返回给前端
 */
@Data
public class UserVO implements Serializable {
    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private Long deptId;
    private Integer status;
    private Date createTime;
}
