package com.tingshuo.common.core.web;

import lombok.Data;

import java.io.Serializable;

/**
 * packageName com.tingshuo.common.core.web
 *
 * @author tingshuo
 * @version JDK 8
 * @className Result
 * @date 2026/1/1 17:16
 * @description 类描述信息
 */
@Data
public class Result <T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<T>();
        r.code = 200;
        r.msg = "success";
        r.data = data;
        return r;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<T>();
        r.code = 500;
        r.msg = msg;
        return r;
    }
}
