package com.tingshuo.order.service.impl;

import com.tingshuo.api.dto.CommonResult;
import com.tingshuo.api.dto.DecreaseRequest;
import com.tingshuo.api.dto.DeductRequest;
import com.tingshuo.order.feign.ProductFeignClient;
import com.tingshuo.order.feign.StorageFeignClient;
import com.tingshuo.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName com.tingshuo.order.service.impl
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderServiceImpl (此处以class为例)
 * @date 2025/12/20-17:48
 * @description 类描述信息 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private StorageFeignClient storageFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @GlobalTransactional(timeoutMills = 60000, name = "create-order-tx")
    @Override
    public void createOrder(Long userId, Long productId, Integer count) {
        // 1. 创建订单（本地 DB 操作）

        // 2. 扣库存
        CommonResult<String> deduct = storageFeignClient.deduct(new DeductRequest(productId, count));
        if (deduct.getCode() != 200) {
            throw new RuntimeException("库存扣减失败: " + deduct.getMessage());
        }

        // 3. 减商品
        CommonResult<String> decrease = productFeignClient.decrease(new DecreaseRequest(productId, count));
        if (decrease.getCode() != 200) {
            throw new RuntimeException("商品减少失败: " + decrease.getMessage());
        }

        // 模拟异常测试回滚
        // if (count > 10) throw new RuntimeException("人为触发回滚！");
    }
}
