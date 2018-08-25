package com.code.test.test;

/**
 * @author wanghongen
 * @date 7/25/18 10:32 PM
 */
public class Test2 {

  public static void main(String[] args) {
    Thread t = new Thread() {
      public void run() {
       pong();
      }
    };
    t.run();
    System.out.println("ping");
  }

  static void pong() {
    System.out.println("pong");
  }

}
