package com.neko.seed.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.neko.seed.common.annotation.Auth;
import com.neko.seed.common.entity.Result;
import com.neko.seed.common.enums.TokenType;
import com.neko.seed.common.exception.ServiceException;
import com.neko.seed.common.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/hello")
    public Result hello() {
        return new Result().success("Hello,world.");
    }

    @GetMapping("/error")
    public Result error() {
        throw new ServiceException(10400, "Error example");
    }

    @GetMapping("/jwt")
    public Result setJwt() {
        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("accountId", 1);
        return new Result().success(tokenService.generate(TokenType.ACCESS,payload,1));
    }

    @PostMapping("/jwt")
    public Result getJwt() {
        return new Result().success(tokenService.parse(TokenType.ACCESS,"eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOjEsInN1YiI6ImFjY2Vzc1Rva2VuIiwiZXhwIjoxNTQ0MjAxMjQxfQ.-grOZCpH3sariP1_kIRH-qJBwMPkl_YkFOM04aFY72o"));
    }

    @GetMapping("/auth")
    public Result auth(@Auth int accountId) {
        return new Result().success(accountId);
    }
}