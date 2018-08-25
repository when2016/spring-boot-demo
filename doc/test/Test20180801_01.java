package com.code.test.test;

/**
 * @author wanghongen
 * @date 8/1/18 2:52 PM
 */
public class Test20180801_01 {

  public static  Test20180801_01 t1 = new Test20180801_01();
  public static Test20180801_01 t2 = new Test20180801_01();

  {
    System.out.println("构造块");
  }

  static {
    System.out.println("静态块");
  }

  public static void main(String[] args) {
    Test20180801_01 t = new Test20180801_01();
  }

}
