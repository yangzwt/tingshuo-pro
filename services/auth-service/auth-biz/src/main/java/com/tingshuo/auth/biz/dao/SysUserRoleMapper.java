package com.tingshuo.auth.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.auth.biz.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tingshuo
 * @version JDK 8
 * @className SysUserRoleMapper
 * @date 2026/1/19 21:11
 * @description 接口描述信息 用户角色Mapper
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
    @Select("SELECT role_id FROM auths.sys_user_role WHERE user_id = #{userId}")
    List<Long> selectRoleIdsByUserId(@Param("userId") Long userId);
}
