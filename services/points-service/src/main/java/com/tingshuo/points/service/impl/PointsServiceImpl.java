package com.tingshuo.points.service.impl;

import com.tingshuo.points.dao.PointsRecordMapper;
import com.tingshuo.points.dao.UserPointsMapper;
import com.tingshuo.points.entity.PointsRecordEntity;
import com.tingshuo.points.entity.UserPointsEntity;
import com.tingshuo.points.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsServiceImpl
 * @date 2026/1/24 10:20
 * @description 类描述信息 积分服务实现类
 */
@Service
public class PointsServiceImpl implements PointsService {
    @Autowired
    private UserPointsMapper userPointsMapper;
    @Autowired
    private PointsRecordMapper pointsRecordMapper;

    /**
     * 添加积分添加本地事务
     * @param userId 用户ID
     * @param points 积分数
     * @param reason 积分原因
     */
    @Override
    @Transactional
    public void addPoints(Long userId, Integer points, String reason) {
// 初始化用户积分（如果不存在）
        UserPointsEntity exists = userPointsMapper.selectById(userId);
        if (exists == null) {
            UserPointsEntity newPoints = new UserPointsEntity();
            newPoints.setUserId(userId);
            newPoints.setTotalPoints(points);
            userPointsMapper.insert(newPoints);
        } else {
            int rows = userPointsMapper.addPoints(userId, points);
            if (rows == 0) {
                throw new RuntimeException("积分更新失败");
            }
        }

        // 查询最新余额
        Integer balance = userPointsMapper.selectById(userId).getTotalPoints();

        // 记录日志
        PointsRecordEntity record = new PointsRecordEntity();
        //record.setId(null);
        record.setUserId(userId);
        record.setChangeValue(points);
        record.setReason(reason);
        record.setBalanceAfter(balance);
        pointsRecordMapper.insert(record);
    }
}
