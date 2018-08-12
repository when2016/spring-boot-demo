package com.wanghongen.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    System.out.println("///////////////////////////////hello////////////");
    return "Welcome to reactive world";
  }

}
