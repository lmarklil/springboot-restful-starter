package com.neko.seed.common.exception;

import com.neko.seed.common.result.Result;
import com.neko.seed.common.result.ResultDTO;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(Exception.class)
    public ResultDTO exception() {
        return Result.error("ERROR",500);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultDTO noHandlerFoundException() {
        return Result.error("NOT FOUND",404);
    }

    @ExceptionHandler(ServiceException.class)
    public ResultDTO serviceException(ServiceException e) {
        return Result.fail(e.getErrorCode(), e.getMessage(), e.getCode());
    }
}