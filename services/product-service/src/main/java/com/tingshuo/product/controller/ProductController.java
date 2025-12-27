package com.tingshuo.product.controller;

import com.tingshuo.api.utils.CommonResult;
import com.tingshuo.product.entity.ProductEntity;
import com.tingshuo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * packageName com.tingshuo.product.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className ProductController
 * @date 2025/12/27 15:40
 * @description 类描述信息
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ProductEntity getProduct(@PathVariable Long id) {
        return productService.getProduct(id); // 返回 entity，但 JSON 结构与 DTO 一致即可
    }
    /**
     * 扣减商品库存（供 order 服务调用）
     * POST /product/{id}?count=1
     */
    @PostMapping("/product/decrease/{id}")
    public CommonResult<String> decrease(
            @PathVariable("id") Long id,
            @RequestParam(defaultValue = "1") Integer count) {

        productService.decrease(id, count);
        return CommonResult.success("商品 [id=" + id + "] 库存已扣减 " + count + " 件");
    }

}
