package com.tingshuo.storage.service;

/**
 * 类文件描述: 库存服务接口
 */
public interface StorageService {
    /**
     * 减库存
     * @param productId
     * @param count
     */
    void deduct(Long productId, Integer count);
}
