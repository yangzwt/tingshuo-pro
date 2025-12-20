package com.tingshuo.api.dto;

import lombok.Data;
/**
 * packageName com.tingshuo.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className DecreaseRequest (此处以class为例)
 * @date 2025/12/20-17:27
 * @description 类描述信息 减库存请求
 */
@Data
public class DecreaseRequest {
    private Long productId;
    private Integer count;

    public DecreaseRequest(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
