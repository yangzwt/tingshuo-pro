package com.tingshuo.common.core.constant;

/**
 * packageName com.tingshuo.common.core.constant
 *
 * @author tingshuo
 * @version JDK 8
 * @className SecurityConstants
 * @date 2026/1/1 17:12
 * @description 类描述信息 安全常量
 */
public interface SecurityConstants {
    String HEADER_USER_ID = "X-User-ID";
    String HEADER_USERNAME = "X-Username";
    String LOGIN_TYPE_WEB = "WEB";
    String LOGIN_TYPE_APP = "APP";
    String CACHE_PREFIX_TOKEN = "auth:token:";
}
