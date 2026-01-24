package com.tingshuo.points.service;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsService
 * @date 2026/1/24 10:19
 * @description 接口描述信息 积分服务
 */
public interface PointsService {
    /**
     * 添加积分
     * @param userId 用户ID
     * @param points 积分
     * @param reason 理由
     */
    public void addPoints(Long userId, Integer points, String reason);
}
