package com.tingshuo.points.controller;

import com.tingshuo.common.core.web.Result;
import com.tingshuo.points.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsController
 * @date 2026/1/24 9:58
 * @description 类描述信息 积分服务
 */
@RestController
@RequestMapping("/api/points")
public class PointsController {
    @Autowired
    private PointsService pointsService;

    @PostMapping("/add")
    public Result<Boolean> addPoints(@RequestParam Long userId,
                                     @RequestParam Integer points,
                                     @RequestParam String reason) {
        pointsService.addPoints(userId, points, reason);
        return Result.success(true);
    }
}
