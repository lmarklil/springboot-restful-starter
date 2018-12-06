package com.neko.seed.common.result;

import lombok.Getter;

@Getter
public class ResultDTO<T> {
    private int code;
    private String message;
    private T data;

    public ResultDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}