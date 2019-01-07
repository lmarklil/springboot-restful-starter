package com.neko.seed.auth.annotation.resolver;

import com.neko.seed.auth.annotation.Auth;
import com.neko.seed.auth.enums.TokenSubject;
import com.neko.seed.auth.exception.AuthException;
import com.neko.seed.auth.exception.TokenException;
import com.neko.seed.auth.exception.UnauthorizedException;
import com.neko.seed.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 认证注解参数解析器
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class AuthMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @Auth注解默认的参数类型为Long，请根据实际需求修改类型
        return parameter.getParameterType().isAssignableFrom(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 从Header取出AccessToken
        String token = webRequest.getHeader("Access-Token");
        // 判断Token是否为空
        if (token != null && token != "") {
            try {
                // 从Token中获取Id并捕获异常
                return tokenService.parse(TokenSubject.ACCESS, token).getBody().get("id");
            } catch (TokenException e) {
                // Token失效，抛出认证异常
                throw new AuthException();
            }
        } else if (!parameter.getParameterAnnotation(Auth.class).required()) {
            // Token为空，判断Token是否可以为空
            return null;
        } else {
            // Token不能为空，抛出未登录异常
            throw new UnauthorizedException();
        }
    }

}