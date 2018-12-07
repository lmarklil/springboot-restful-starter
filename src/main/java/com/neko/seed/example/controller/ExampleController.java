package com.neko.seed.example.controller;

import com.neko.seed.common.entity.Result;
import com.neko.seed.common.exception.ServiceException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/hello")
    public Result hello() {
        return new Result().success("Hello,world.");
    }

    @GetMapping("/error")
    public Result error() {
        throw new ServiceException(10400, "Error example");
    }
}