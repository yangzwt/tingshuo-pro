package com.tingshuo.order.feign;

import com.tingshuo.api.utils.CommonResult;
import com.tingshuo.api.dto.DeductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * packageName com.tingshuo.order.feign
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageFeignClient (此处以class为例)
 * @date 2025/12/20-17:01
 * @description 接口描述信息 订单服务调用库存服务
 */
@FeignClient(name = "storage-service")
public interface StorageFeignClient {
    /**
     * 扣减库存
     * @param request
     * @return
     */
    @PostMapping("/storage/deduct")
    CommonResult<String> deduct(@RequestBody DeductRequest request);
}
