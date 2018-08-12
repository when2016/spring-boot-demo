package com.wanghongen.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    System.out.println("///////////////////abcd////////////hello////////////");
    return "Welcome to spring boot xxxxx ssss";
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }


}
