package com.tingshuo.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.order.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName com.tingshuo.order.dao
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderMapper
 * @date 2025/12/20-19:18
 * @description 接口描述信息 订单服务数据访问层
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
}
