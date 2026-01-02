package com.tingshuo.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.tingshuo.auth.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className MenuVO
 * @date 2026/1/1 18:25
 * @description 类描述信息 菜单VO
 */
@Data
public class MenuDTO {
    private Long menuId;
    private String name;
    private String path;
    private String component;
    private Boolean hidden;
    private Integer sort;
    private List<MenuDTO> children = new ArrayList<>();
    @JsonIgnore
    private Long parentId;
}
