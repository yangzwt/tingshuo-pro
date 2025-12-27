package com.tingshuo.order.feign;

import com.tingshuo.api.utils.CommonResult;

import com.tingshuo.api.dto.DecreaseRequest;
import com.tingshuo.order.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * packageName com.tingshuo.order.feign
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductFeignClient (此处以class为例)
 * @date 2025/12/20-17:01
 * @description 类描述信息 订单服务调用商品服务
 */
@FeignClient(name = "tingshuo-product")
public interface ProductFeignClient {
    /**
     * 查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);
    /**
     * 减库存
     * @param id
     * @return
     */
    @PostMapping("/product/decrease/{id}")
    CommonResult<String> decrease(@PathVariable("id") Long id,@RequestParam("count") Integer count);

}
