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
 *  表示扣款请求的相关信息
 *  该类封装了扣款操作所需的基本数据结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductRequest {
    private Long productId;
    private Integer count;
}