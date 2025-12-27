package com.tingshuo.order.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * packageName com.tingshuo.order.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductDTO
 * @date 2025/12/27 15:34
 * @description 类描述信息
 */
@Data
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Integer stock; // 注意：这里用 stock 字段做库存校验
}
