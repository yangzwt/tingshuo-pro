package com.tingshuo.user.service;

/**
 * packageName com.tingshuo.user.service
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserService
 * @date 2025/12/27 15:51
 * @description 接口描述信息
 */
public interface UserService {
    /**
     * 判断用户是否存在
     * @param id
     * @return
     */
  boolean userExists(Long id);
}
