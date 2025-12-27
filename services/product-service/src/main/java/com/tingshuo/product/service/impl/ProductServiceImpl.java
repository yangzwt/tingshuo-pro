package com.tingshuo.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tingshuo.product.dao.ProductMapper;
import com.tingshuo.product.entity.ProductEntity;
import com.tingshuo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName com.tingshuo.product.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductServiceImpl (此处以class为例)
 * @date 2025/12/20-18:08
 * @description 类描述信息 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductEntity getProduct(Long productId) {
        return productMapper.selectById(productId);
    }

    /**
     * 减商品
     * @param productId
     * @param count
     */
    @Transactional
    @Override
    public int decrease(Long productId, Integer count) {
        // 参数验证
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("产品ID不能为空或小于等于0");
        }
        if (count == null || count <= 0) {
            throw new IllegalArgumentException("减少数量不能为空或小于等于0");
        }

        // 使用乐观锁或库存检查，确保库存充足且减少操作安全
        return productMapper.update(null,
                new UpdateWrapper<ProductEntity>()
                        .setSql("stock = stock - " + count) // 注意：count 是整数，可拼接
                        .eq("id", productId)
                        .ge("stock", count));
    }
}
