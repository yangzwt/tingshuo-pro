package com.tingshuo.asset.dto;

import java.io.Serializable;

/**
 * packageName com.tingshuo.user.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className UserDTO (此处以class为例)
 * @date 2025/12/5-21:49
 * @description TODO
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public UserDTO(String id, String name) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
