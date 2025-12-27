package com.tingshuo.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName com.tingshuo.product.dao
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductMapper
 * @date 2025/12/27 15:41
 * @description 接口描述信息
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {
}
