package com.neko.seed.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证注解
 *
 * @author Li Jinhui
 * @update 2019/1/7 09:49
 * @since 2018/12/7 10:00
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    boolean required() default true;
}