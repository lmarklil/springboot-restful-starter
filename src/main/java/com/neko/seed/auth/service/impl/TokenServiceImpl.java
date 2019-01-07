package com.neko.seed.auth.service.impl;

import com.neko.seed.auth.service.TokenService;
import com.neko.seed.auth.exception.TokenException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token服务
 *
 * @author Li Jinhui
 * @since 2018/12/12 15:15
 * @update 2019/01/07 09:46
 **/
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * 注意：
     * 此处为jjwt的简单封装，Token密钥所使用的加密方式为最简单的方式。
     * 实践中建议参考jjwt的文档对该业务层进行进一步的改造。
     **/

    /**
     * Token密钥
     **/
    private static final String SECRECT = "YOUR SECRET + 64 BIT > OVJPIDSFWEcvoOIDF1-232''F]WE=F23=-4VXCNONSFIOEIWI023";

    /**
     * 将密钥转换成byte数组
     **/
    private byte[] getSecret() {
        return Base64.getEncoder().encode(SECRECT.getBytes());
    }

    /**
     * 生成只有Id的Payload
     **/
    private Map<String, Object> buildPayloadOnlyId(long id) {
        return new HashMap<String, Object>() {
            {
                put("id", id);
            }
        };
    }

    /**
     * 生成Token
     **/
    private String buildToken(Object subject, Map<String, Object> payload, int hours) {
        return Jwts.builder().setClaims(payload).setSubject(subject.toString()).setExpiration(new Date(new Date().getTime() + (hours * 36000000)))
                .signWith(SignatureAlgorithm.HS256, getSecret()).compact();
    }

    /**
     * 生成Payload只带Id的Token，有效期为1个小时
     **/
    @Override
    public String generate(Object subject, long id) {
        return buildToken(subject, buildPayloadOnlyId(id), 1);
    }

    /**
     * 生成Payload只带Id的Token，可以自定义有效期
     **/
    @Override
    public String generate(Object subject, long id, int hours) {
        return buildToken(subject, buildPayloadOnlyId(id), hours);
    }

    /**
     * 通过Map设置带Payload的Token，有效期为一个小时
     **/
    @Override
    public String generate(Object subject, Map<String, Object> payload) {
        return buildToken(subject, payload, 1);
    }

    /**
     * 通过Map设置带Payload的Token，可以自定义有效期
     **/
    @Override
    public String generate(Object subject, Map<String, Object> payload, int hours) {
        return buildToken(subject, payload, hours);
    }

    /**
     * 格式化Token，返回一个Jws对象
     **/
    @Override
    public Jws<Claims> parse(Object subject, String token) {
        try {
            return Jwts.parser().requireSubject(subject.toString()).setSigningKey(getSecret()).parseClaimsJws(token);
        } catch (JwtException e) {
            throw new TokenException();
        }
    }

}