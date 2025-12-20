package com.tingshuo.storage.controller;

import com.tingshuo.api.utils.CommonResult;
import com.tingshuo.api.dto.DeductRequest;
import com.tingshuo.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.storage.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className StorageController (此处以class为例)
 * @date 2025/12/20-17:36
 * @description 类描述信息 仓库服务控制层
 */
@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/deduct")
    public CommonResult<String> deduct(@RequestBody DeductRequest request) {
        storageService.deduct(request.getProductId(), request.getCount());
        return CommonResult.success("库存扣减成功");
    }
}
