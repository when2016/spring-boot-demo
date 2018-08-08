package com.wanghongen.demo.test;

/**
 * @author wanghongen
 * @date 8/1/18 2:30 PM
 */
public class Test20180801 {
  String str = new String("hello");
  char[] ch = {'a','b'};

  public static void main(String[] args) {
    Test20180801 t = new Test20180801();
    t.change(t.str,t.ch);

    System.out.print(t.str+"and ");
    System.out.println(t.ch);

    int x,y;
    x=5>>2;
    System.out.println(x);
    y=x>>>2;
    System.out.println(y);

    System.out.println("b"+10);
    System.out.println('b'+10);

    System.out.println('A'+0);
    System.out.println('B'+0);
    System.out.println('Z'+0);

    System.out.println('a'+0);
    System.out.println('b'+0);

  }

  private void change(String str, char[] ch) {
    str="test ok";
    ch[0]='c';
  }

}
