package com.wanghongen.demo.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2018/7/4
 */
public class Test {

  public static void main(String[] args) {
    Singleton.INSTANCE.whateverMethod();
    Map map = Collections.synchronizedMap(new HashMap<>());
  }

}
