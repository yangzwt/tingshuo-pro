package com.tingshuo.auth.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.auth.biz.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tingshuo
 * @version JDK 8
 * @className SysRoleMenuMapper
 * @date 2026/1/19 21:09
 * @description 接口描述信息 角色菜单Mapper
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    List<Long> selectMenuIdsByRoleIds(List<Long> roleIds);
}
