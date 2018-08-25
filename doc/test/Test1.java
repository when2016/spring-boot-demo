package com.code.test.test;

/**
 * @author wanghongen
 * @date 7/25/18 7:55 PM
 */
public class Test1 {

  public static void main(String[] args) {
    int i=10;
    int h=20;


    i=(i+h)-(h=i);
    System.out.println(i);
    System.out.println(h);


    int k=2;
    int j=3;
    k=j;
    j=(k+j)-k;

    System.out.println("k="+k+",j="+j);


    int a=3;
    int b=5;
    a=a^b;
    b=a^b;
    //a=a^b;
    System.out.println("a="+a+" b="+b);


  }

}
