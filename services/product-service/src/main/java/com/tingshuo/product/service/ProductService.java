package com.tingshuo.product.service;

import com.tingshuo.product.entity.ProductEntity;

public interface ProductService {
    /**
     * 根据商品ID查询商品信息
     * @param productId
     * @return
     */
    ProductEntity getProduct(Long productId);
    /**
     * 减库存
     * @param productId
     * @param count
     */
    int decrease(Long productId, Integer count);
}
