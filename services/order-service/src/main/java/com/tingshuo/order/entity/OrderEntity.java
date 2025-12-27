package com.tingshuo.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * packageName com.tingshuo.order.entity
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderEntity
 * @date 2025/12/20-19:15
 * @description 类描述信息 订单实体类
 */
@Data
@TableName("tingshuo_order")
public class OrderEntity {
    //@TableId(value = "id",type = IdType.AUTO)
    @TableId(value = "id",type = IdType.NONE)
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal totalPrice;
    private String status;
}
