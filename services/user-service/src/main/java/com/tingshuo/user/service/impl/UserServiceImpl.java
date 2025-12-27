package com.tingshuo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tingshuo.user.entity.UserEntity;
import com.tingshuo.user.dao.UserMapper;
import com.tingshuo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName com.tingshuo.user.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserServiceImpl
 * @date 2025/12/27 15:55
 * @description 类描述信息
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 判断用户是否存在
     * @param id
     * @return
     */
    @Override
    public boolean userExists(Long id) {
        if (id==null){
            return false;
        }
        return userMapper.exists( new QueryWrapper<UserEntity>().eq("id", id));
    }

}
