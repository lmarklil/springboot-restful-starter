package com.neko.seed.common.service;

import java.util.Map;

import com.neko.seed.common.enums.TokenType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * JWT标准Token认证服务
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public interface TokenService {
    String generate(TokenType subject, Map<String, Object> payload, int hours);

    Jws<Claims> parse(TokenType subject, String token);
}