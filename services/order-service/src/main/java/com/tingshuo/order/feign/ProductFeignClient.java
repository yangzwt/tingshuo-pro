package com.tingshuo.order.feign;

import com.tingshuo.api.utils.CommonResult;

import com.tingshuo.api.dto.DecreaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * packageName com.tingshuo.order.feign
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductFeignClient (此处以class为例)
 * @date 2025/12/20-17:01
 * @description 类描述信息 订单服务调用商品服务
 */
@FeignClient(name = "product-service")
public interface ProductFeignClient {
    /**
     * 减库存
     * @param request
     * @return
     */
    @PostMapping("/product/decrease")
    CommonResult<String> decrease(@RequestBody DecreaseRequest request);
}
