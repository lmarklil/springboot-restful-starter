package com.neko.seed.auth.config;

import com.neko.seed.auth.annotation.resolver.AuthMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvc配置添加Auth注解解析器
 *
 * @author: Li Jinhui
 * @since: 2018/12/04 11:00
 **/
@Configuration
public class WebMvcConfigFromAuth implements WebMvcConfigurer {

    /**
     * 添加认证注解参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authMethodArgumentResolver());
    }

    /**
     * 注册认证注解参数解析器
     */
    @Bean
    public AuthMethodArgumentResolver authMethodArgumentResolver() {
        return new AuthMethodArgumentResolver();
    }

}