package com.tingshuo.points.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.points.entity.PointsRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsRecordMapper
 * @date 2026/1/24 11:28
 * @description 接口描述信息 积分记录
 */
@Mapper
public interface PointsRecordMapper extends BaseMapper<PointsRecordEntity> {
}
