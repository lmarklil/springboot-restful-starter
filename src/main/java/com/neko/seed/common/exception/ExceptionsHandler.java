package com.neko.seed.common.exception;

import com.neko.seed.common.entity.Result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常接管
 *
 * @author Li Jinhui
 * @param <T>
 * @since 2018/12/6
 */
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * 基本异常
     * 
     * @param <T>
     * 
     * @since 2018/12/6
     */
    @ExceptionHandler(Exception.class)
    public Result excepThreadDeath() {
        return new Result().error("ERROR",500);
    }

    /**
     * 404异常
     * 
     * @since 2018/12/6
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundException() {
        return new Result().error("NOT FOUND",404);
    }

    /**
     * 服务异常
     * 
     * @since 2018/12/6
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return new Result().fail(e.getErrorCode(), e.getMessage(), e.getCode());
    }
}