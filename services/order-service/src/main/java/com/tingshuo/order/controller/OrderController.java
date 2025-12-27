package com.tingshuo.order.controller;

import com.tingshuo.api.dto.OrderDTO;
import com.tingshuo.api.utils.CommonResult;
import com.tingshuo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.order.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className OrderController
 * @date 2025/12/27 14:27
 * @description 类描述信息
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/order/create")
    public CommonResult create(@RequestBody OrderDTO dto) {
        orderService.createOrder(dto.getUserId(), dto.getProductId(), dto.getCount());
        return CommonResult.success("下单成功");
    }
}
