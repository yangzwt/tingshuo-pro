package com.tingshuo.points.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.points.entity.PointsRecordEntity;
import com.tingshuo.points.entity.UserPointsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsMapper
 * @date 2026/1/24 10:18
 * @description 类描述信息 积分服务数据访问接口
 */
@Mapper
public interface UserPointsMapper extends BaseMapper<UserPointsEntity> {
    /**
     *
     * @param userId
     * @param delta
     * @return
     */
    @Update("UPDATE user_points SET total_points = total_points + #{delta} WHERE user_id = #{userId}")
    int addPoints(@Param("userId") Long userId, @Param("delta") Integer delta);

    /**
     *  添加积分记录
     * @param record
     * @return
     */
    boolean insertRecord(PointsRecordEntity record);
}
