package com.tingshuo.order.service.impl;

import com.tingshuo.api.utils.CommonResult;
import com.tingshuo.api.dto.DecreaseRequest;
import com.tingshuo.api.dto.DeductRequest;
import com.tingshuo.order.dao.OrderMapper;
import com.tingshuo.order.dto.ProductDTO;
import com.tingshuo.order.entity.OrderEntity;
import com.tingshuo.order.feign.ProductFeignClient;
import com.tingshuo.order.feign.StorageFeignClient;
import com.tingshuo.order.feign.UserFeignClient;
import com.tingshuo.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    private OrderMapper orderMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private StorageFeignClient storageFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @GlobalTransactional
    @Override
    public void createOrder(Long userId, Long productId, Integer count) {

        // 1. 校验用户
        if (!userFeignClient.userExists(userId)){
            throw new RuntimeException("用户不存在");
        }
        // 2. 查询商品
        ProductDTO product = productFeignClient.getProduct(productId);
        if (product == null || product.getStock() < count) {
            throw new RuntimeException("商品库存不足");
        }
        // 1. 创建订单（本地 DB 操作）
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setProductId(productId);
        orderEntity.setCount(count);
        orderEntity.setTotalPrice(new BigDecimal("6999").multiply(BigDecimal.valueOf(count)));
        orderEntity.setStatus("0");
        orderEntity.setId(null);
        orderMapper.insert(orderEntity);
        System.out.println("订单创建成功，订单ID: "+orderEntity.getId());


        // 2. 扣库存
        CommonResult<String> deduct = storageFeignClient.deduct(productId, count);
        if (deduct.getCode() != 200) {
            throw new RuntimeException("库存扣减失败: " + deduct.getMessage());
        }

        // 3. 减商品
        CommonResult<String> decrease = productFeignClient.decrease(productId, count);
        if (decrease.getCode() != 200) {
            throw new RuntimeException("商品减少失败: " + decrease.getMessage());
        }

        // 模拟异常测试回滚
        // if (count > 10) throw new RuntimeException("人为触发回滚！");
    }
}
