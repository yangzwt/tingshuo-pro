package com.tingshuo.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * packageName com.tingshuo.storage.entity
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageEntity
 * @date 2025/12/27 14:42
 * @description 类描述信息
 */
@Data
@TableName("tingshuo_storage")
public class StorageEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long productId;
    private Integer total;     // 总库存
    private Integer residue;   // 可用库存
}
