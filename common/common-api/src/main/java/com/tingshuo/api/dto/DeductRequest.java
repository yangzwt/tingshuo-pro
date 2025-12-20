package com.tingshuo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName com.tingshuo.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className DeductRequest (此处以class为例)
 * @date 2025/12/20-17:01
 * @description 类描述信息 减库存请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductRequest {
    private Long productId;
    private Integer count;
}