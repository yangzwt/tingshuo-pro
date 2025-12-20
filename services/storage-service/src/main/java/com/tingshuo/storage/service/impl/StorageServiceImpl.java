package com.tingshuo.storage.service.impl;

import com.tingshuo.storage.service.StorageService;
import org.springframework.stereotype.Service;

/**
 * packageName com.tingshuo.storage.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageServiceImpl (此处以class为例)
 * @date 2025/12/20-17:40
 * @description 类描述信息 库存服务实现类
 */
@Service
public class StorageServiceImpl implements StorageService {
    /**
     * 减库存
     * @param productId
     * @param count
     */
    @Override
    public void deduct(Long productId, Integer count) {

    }
}
