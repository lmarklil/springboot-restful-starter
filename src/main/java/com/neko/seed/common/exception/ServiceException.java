package com.neko.seed.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private int code;
    private String message;
    private int errorCode;

    public ServiceException(int errorCode, String message) {
        this.code = 400;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ServiceException(int errorCode, String message, int code) {
        this.code = code;
        this.message = message;
        this.errorCode = errorCode;
    }
}