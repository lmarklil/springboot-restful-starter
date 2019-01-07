package com.neko.seed.user.exception;

import com.neko.seed.base.exception.ServiceException;

public class PasswordErrorException extends ServiceException {
    public PasswordErrorException() {
        super(10400, "密码错误");
    }
}