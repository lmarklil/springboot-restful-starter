package com.neko.seed.common.entity;

import lombok.Getter;

/**
 * Result实体类
 *
 * @author Li Jinhui
 * @since 2018/12/6
 */
@Getter
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result setResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(200, "SUCCESS", null);
    }

    public Result success(T data) {
        return setResult(200, "SUCCESS", data);
    }

    public Result fail(T errorCode, String message) {
        return setResult(400, message, errorCode);
    }

    public Result fail(T errorCode, String message, int code) {
        return setResult(code, message, errorCode);
    }

    public Result error(String message, int code) {
        return setResult(code, message, null);
    }
}