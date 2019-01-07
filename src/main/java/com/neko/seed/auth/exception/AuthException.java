package com.neko.seed.auth.exception;

import com.neko.seed.base.exception.ServiceException;

/**
 * 认证异常
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class AuthException extends ServiceException {
    public AuthException() {
        super("Authentication failed", 403);
    }
}
