package com.wanghongen.demo.code;

/**
 * Created by wang on 2018/7/10
 */
public class Test2 {

  static boolean foo(char c) {
    System.out.print(c);
    return true;
  }

  public static void main(String[] args) {
    int i = 0;
    for (foo('A'); foo('B') && (i < 2);foo('C')) {
      i++;
      foo('D');
    }
  }

}
