package com.tingshuo.storage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingshuo.storage.entity.StorageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * packageName com.tingshuo.storage.dao
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageMapper
 * @date 2025/12/27 14:35
 * @description 接口描述信息
 */
@Mapper
public interface StorageMapper extends BaseMapper<StorageEntity> {
    /**
     * 扣减库存（AT 模式下必须是可回滚的 UPDATE）
     */
    @Update("UPDATE tingshuo_storage SET residue = residue - #{count} WHERE product_id = #{productId} AND residue >= #{count}")
    int deduct(@Param("productId") Long productId, @Param("count") Integer count);
}
