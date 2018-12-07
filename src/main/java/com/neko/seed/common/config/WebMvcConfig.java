package com.neko.seed.common.config;

import java.util.List;

import com.neko.seed.common.annotation.resolver.AuthMethodArgumentResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 *
 * @author Li Jinhui
 * @since 2018/12/4
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${security.origins}")
    private String origins;

    /**
     * 开启跨域
     * 
     * @since 2018/12/4
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins(origins)
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * 添加认证注解参数解析器
     * 
     * @since 2018/12/7
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authMethodArgumentResolver());
    }

    /**
     * 注册认证注解参数解析器
     * 
     * @since 2018/12/7
     */
    @Bean
    public AuthMethodArgumentResolver authMethodArgumentResolver() {
        return new AuthMethodArgumentResolver();
    }
}