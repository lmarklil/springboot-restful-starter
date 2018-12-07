package com.neko.seed.common.annotation.resolver;

import com.neko.seed.common.enums.TokenType;
import com.neko.seed.common.exception.ServiceException;
import com.neko.seed.common.service.TokenService;

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
        return parameter.getParameterType().isAssignableFrom(int.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("Access-Token");
        if(token!=null) {
            return tokenService.parse(TokenType.ACCESS, token).getBody().get("accountId");
        } else {
            throw new ServiceException("Sign required", 401);
        }
    }

}