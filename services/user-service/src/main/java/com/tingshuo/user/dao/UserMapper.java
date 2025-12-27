package com.tingshuo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName com.tingshuo.user.dao
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserMapper
 * @date 2025/12/27 15:56
 * @description 接口描述信息
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
