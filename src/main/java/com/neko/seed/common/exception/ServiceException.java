package com.neko.seed.common.exception;

import lombok.Getter;

/**
 * 服务异常类
 *
 * @author Li Jinhui
 * @since 2018/12/6
 */
@Getter
public class ServiceException extends RuntimeException {
    private int code;
    private String message;
    private Integer errorCode;

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

    public ServiceException(String message, int code) {
        this.code = code;
        this.message = message;
    }
}