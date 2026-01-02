package com.tingshuo.auth.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * packageName com.tingshuo.auth.api.vo
 *
 * @author tingshuo
 * @version JDK 8
 * @className MenuVO
 * @date 2026/1/2 14:46
 * @description 类描述信息 菜单VO
 */
@Data
public class MenuVO implements Serializable {
    private Long id;
    private String menuName;
    private Long parentId;
    private String path;
    private String component;
    private String icon;
    private Integer type;
    private String permission;
    private Integer sort;
}
