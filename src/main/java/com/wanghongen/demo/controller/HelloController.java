package com.wanghongen.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


  @GetMapping("/hello")
  public String hello() {

    return "Welcome to spring boot";
  }

  @GetMapping("/aa")
  public String index() {
    System.out.println();
    return "王红恩：Hello World!SSSSeeeeeeeeeeeeee";
  }


}
