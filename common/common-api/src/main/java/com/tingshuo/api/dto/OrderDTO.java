package com.tingshuo.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderDTO
 * @date 2025/12/20 18:42
 * @description 类描述信息
 */
@Data
public class OrderDTO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 购买数量
     */
    private Integer count;
}
