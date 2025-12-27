package com.tingshuo.storage.service.impl;

import com.tingshuo.storage.dao.StorageMapper;
import com.tingshuo.storage.entity.StorageEntity;
import com.tingshuo.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private StorageMapper storageMapper;
    /**
     * 减库存
     * @param productId
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deduct(Long productId, Integer count) {
        StorageEntity storageEntity = storageMapper.selectById(productId);
        if (storageEntity == null || storageEntity.getResidue() < count){
            throw new RuntimeException("库存不足");
        }
        int deduct = storageMapper.deduct(productId, count);
        if (deduct == 0) {
            throw new RuntimeException("扣减库存失败，可能并发冲突或库存不足");
        }
        System.out.println("商品 " + productId + " 扣减库存 " + count + " 成功");
    }
}
