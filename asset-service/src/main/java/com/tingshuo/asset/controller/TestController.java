package com.tingshuo.asset.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName com.tingshuo.asset.controller
 *
 * @author tingshuo
 * @version JDK 8
 * @className TestController (此处以class为例)
 * @date 2025/12/6-11:13
 * @description 测试流量控制相关方法
 */
@RestController
public class TestController {
    @GetMapping("/test-sentinel")
    public String testSentinel() {
        Entry entry = null;
        try {
            entry = SphU.entry("testResource");

            // 业务逻辑
            if (System.currentTimeMillis() % 2 == 0) {
                throw new RuntimeException("故意抛异常！");
            }
            return "OK";

        } catch (BlockException ex) {
            // Sentinel 规则触发（熔断/流控）
            System.out.println("【BlockException】被 Sentinel 熔断/限流"+ex);
            return "【BLOCKED】熔断生效！";

        } catch (RuntimeException ex) {
            // 业务异常（会被 Sentinel 统计为异常！）
            return "【FALLBACK】业务异常: " + ex.getMessage();

        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
    @GetMapping("/manual")
    public String manual() {
        try (Entry entry = SphU.entry("manualTest")) {
            return "Manual OK";
        } catch (BlockException e) {
            return "Blocked";
        }
    }
}
