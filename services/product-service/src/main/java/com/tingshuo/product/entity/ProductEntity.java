package com.tingshuo.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * packageName com.tingshuo.product.entity
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductEntity
 * @date 2025/12/27 15:29
 * @description 类描述信息
 */
@Data
@TableName("tingshuo_product")
public class ProductEntity {
    @TableId(value = "id", type = IdType.NONE)
    private Long id;
    private String name;
    private Integer stock; // 字段名必须一致！
}
