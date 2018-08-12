package com.wanghongen.demo.jvm;

/**
 * Created by wang on 2018/7/12
 */
public class TestGC {

  public static void main(String[] args) {
    Object obj = new Object();
    System.gc();
    System.out.println();
    obj = new Object();
    obj = new Object();
    System.gc();
    System.out.println();
  }

}
