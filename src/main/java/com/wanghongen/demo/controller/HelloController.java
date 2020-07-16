package com.wanghongen.demo.controller;

import com.wanghongen.demo.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${gwf.name}")
    private String msg;

    @RequestMapping("/hello")
    public String hello() {
        int num = 1 / 0;
        return this.msg;
    }

    @RequestMapping("/hello2")
    public String hello2() {
        //int num = 1 / 0;
        throw new BusinessException("100", "密码错误");
        ///return this.msg;
    }
}