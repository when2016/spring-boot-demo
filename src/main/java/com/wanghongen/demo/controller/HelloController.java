package com.wanghongen.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }
  @PostMapping("/hello")
  public String hello2() {
    return "post hello";
  }

  @GetMapping("/aa")
  public String index() {
    System.out.println();
    return "王红恩：Hello World!SSSSeeeeeeeeeeeeee";
  }


}
