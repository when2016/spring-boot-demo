package com.code.test.test;

/**
 * @author wanghongen
 * @date 8/24/18 2:04 PM
 */
public class Test {


  public static void main(String[] args) {
    String data = "abcdefj";
    if(data.indexOf("a") >-1 ) {
      System.out.println(data.indexOf("a"));
      System.out.println(data.indexOf("ab"));
      System.out.println(data.indexOf("bc"));
    }
    data = null;
    if(data.indexOf("a") > -1) {
      System.out.println(data.indexOf("fasfasfsafafsdfsfa"));
      System.out.println(data.indexOf("a"));
      System.out.println(data.indexOf("a"));
      System.out.println(data.indexOf("bdfdsfafsdafsa"));
      System.out.println(data.indexOf("a"));
      System.out.println(data.indexOf("sfafdssadfdsfsda"));
      System.out.println(data.indexOf("a"));
    }
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
    System.out.println(data);
  }
}
