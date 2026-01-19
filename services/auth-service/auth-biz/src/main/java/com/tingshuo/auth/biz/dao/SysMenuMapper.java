package com.tingshuo.auth.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.auth.biz.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * packageName com.tingshuo.auth.mapper
 *
 * @author tingshuo
 * @version JDK 8
 * @className SysMenuMapper
 * @date 2026/1/1 18:33
 * @description 接口描述信息 菜单Mapper
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    @Select("SELECT m.* " +
            "FROM sys_menu m " +
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " +
            "LEFT JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} " +
            "  AND m.status = 1 " +
            "ORDER BY m.sort")
    List<SysMenuEntity> selectMenusByUserId(@Param("userId") Long userId);
    @Select("<script>" +
            "SELECT permission FROM auths.sys_menu WHERE id IN " +
            "<foreach item='id' collection='menuIds' open='(' separator=',' close=')'>#{id}</foreach>" +
            " AND permission IS NOT NULL" +
            "</script>")
    List<String> selectPermissionsByIds(@Param("menuIds") List<Long> menuIds);
}
