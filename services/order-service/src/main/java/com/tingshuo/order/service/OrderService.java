package com.tingshuo.order.service;

/**
 * 类文件描述: 订单服务接口
 */
public interface OrderService {
    /**
     * 创建订单
     * @param userId
     * @param productId
     * @param count
     */
    void createOrder(Long userId, Long productId, Integer count);
}
