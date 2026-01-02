package com.tingshuo.auth.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.tingshuo.auth.biz.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * packageName com.tingshuo.auth.mapper
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysUserMapper
 * @date 2026/1/1 18:27
 * @description 接口描述信息 用户Mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    @Select("SELECT * FROM sys_user WHERE username = #{username} AND status = 1")
    SysUserEntity findByUsername(String username);
}
