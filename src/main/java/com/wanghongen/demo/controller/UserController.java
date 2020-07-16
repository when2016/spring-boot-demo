package com.wanghongen.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info1")
    public String test(){
        log.info("/user/info1");

        throw new NullPointerException("UserController have exception");

    }
}
