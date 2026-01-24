package com.tingshuo.points.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tingshuo
 * @version JDK 8
 * @className UserPointsEntity
 * @date 2026/1/24 10:15
 * @description 类描述信息 用户积分
 */
@Data
@TableName("user_points")
public class UserPointsEntity {
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;
    private Integer totalPoints;
}
