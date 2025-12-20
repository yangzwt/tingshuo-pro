package com.tingshuo.api.dto;

import lombok.Data;

/**
 * packageName com.tingshuo.api.dto
 *
 * @author tingshuo
 * @version JDK 8
 * @className CommonResult (此处以class为例)
 * @date 2025/12/20-17:01
 * @description 类描述信息
 */
@Data
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;
    private CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "success", data);
    }

    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(500, message, null);
    }
}
