package com.neko.seed.base.handler;

import com.neko.seed.base.entity.Result;
import com.neko.seed.base.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常接管
 *
 * @author Li Jinhui
 * @update 2019/1/7 09:22
 * @since 2018/12/6 10:00
 */
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * 注意：
     * 启用全局异常接管后，没有在此处定义拦截的异常都会默认返回500错误。
     * 若需要自定义拦截的异常，请在此处定义拦截。
     * 若需要输出异常的日志日志，请使用logger输出。
     */
    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    /**
     * 基本异常
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        logger.error(e.getMessage(), e);
        return new Result().fail("Error", 500);
    }

    /**
     * 请求路径无法找到异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result notFoundException() {
        return new Result().fail("Not found", 404);
    }

    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException() {
        return new Result().fail("Method not allowed", 405);
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public Result parameterException() {
        return new Result().fail("Parameter error", 403);
    }

    /**
     * 上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceededException() {
        return new Result().fail("File is too large", 403);
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return new Result().fail(e.getData(), e.getMessage(), e.getCode());
    }

}