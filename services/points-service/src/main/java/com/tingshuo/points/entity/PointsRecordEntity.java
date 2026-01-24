package com.tingshuo.points.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author tingshuo
 * @version JDK 8
 * @className PointsRecordEntity
 * @date 2026/1/24 10:16
 * @description 类描述信息 积分记录
 */
@Data
@TableName("points_record")
public class PointsRecordEntity {
    //@TableId(type = IdType.AUTO)
    @TableId(value = "id",type = IdType.NONE)
    private Long id;
    private Long userId;
    private Integer changeValue;
    private String reason;
    private Integer balanceAfter;
    private Date createTime;
}
