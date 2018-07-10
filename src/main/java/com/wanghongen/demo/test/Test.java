package com.wanghongen.demo.test;

/**
 * Created by wang on 2018/7/4
 */
public class Test {

  public static void main(String[] args) {
    Singleton.INSTANCE.whateverMethod();

    System.out.println(Math.round(11.5));
    System.out.println(Math.round(-11.5));

  }

}
