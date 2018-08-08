package com.wanghongen.demo.test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author wanghongen
 * @date 7/25/18 7:39 PM
 */
public class Test {

  public static void main(String[] args) {
//    for (int i = 0; i <= Integer.MAX_VALUE; i++) {
//      System.out.println("i=" + i);
//    }
//
//    System.out.println("hello world");

    LinkedList<Integer> aList = new LinkedList<>();
    aList.add(1);
    aList.add(5);
    aList.add(8);
    aList.add(8);

    LinkedList<Integer> bList = new LinkedList<>();
    bList.add(2);
    bList.add(6);
    bList.add(4);
    bList.add(5);

    Iterator<Integer> iit = aList.iterator();
    Iterator<Integer> iit2 = bList.iterator();

    int log = 0;
    StringBuffer buffer = new StringBuffer();
    String result = "";
    while(iit2.hasNext()) {
      int b = iit2.next();
      System.out.println(b);
      if (iit.hasNext()) {
        int a = iit.next();
        System.out.println(a);
        buffer.append((log + a + b)%10);
        log = a + b > 10 ?  1 : 0;
      }
      result = log == 0 ? String.valueOf(b).concat(buffer.toString()) : String.valueOf( b + log).concat(buffer.toString());
    }
    System.out.println(result);

//    boolean isMin = aList.size() > bList.size();
//    if(isMin) {
//
//      for(int i = 0, len = bList.size(); i< len; i++) {
//
//      }
//    }
//
//    while(iit.hasNext()) {
//
//      System.out.println(iit.next());
//    }
//
//
//    int k = Math.max(aList.size(), bList.size());
//
//    for (int i = 0; i < k; i++) {
//      int a = aList.getLast();
//      int b = bList.getLast();
//
//
//      aList.offerLast(i);
//
//
//    }
//

  }

}
