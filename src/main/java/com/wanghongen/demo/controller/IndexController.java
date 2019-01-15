package com.wanghongen.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * 默认的HTML
 *
 * @author wang  2017/8/11 下午1:36
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/upload"}, method = RequestMethod.GET)
    public String index() {
        System.out.println("IndexController");
        return "index";
    }


}
