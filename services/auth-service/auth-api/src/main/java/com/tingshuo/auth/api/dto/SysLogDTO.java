package com.tingshuo.auth.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.auth.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysLogDTO
 * @date 2026/1/1 19:50
 * @description 类描述信息 系统日志
 */
@Data
public class SysLogDTO {
    private Long userId;
    private String title;
    private String method;
    private String requestIp;
    private Integer time;
}
