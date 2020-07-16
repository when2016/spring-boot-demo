package com.wanghongen.demo.controller;

import com.wanghongen.demo.bean.HelloWorld;
import com.wanghongen.demo.exception.DemoException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    private String myname = "王红恩";

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public HelloWorld say() {
        return new HelloWorld(myname, "man");
    }

    @RequestMapping(value = "/say2", method = RequestMethod.GET)
    public boolean say2() {
        if (true)
            throw new DemoException("error", 50001);
        return true;

    }
}
