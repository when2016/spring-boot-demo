package com.code.test.test;

import java.util.LinkedList;

/**
 * @author wanghongen
 * @date 7/31/18 2:55 PM
 */
public class linkedMain {

  public static void main(String[] args) {

    LinkedList<Integer> aList = new LinkedList<>();
    aList.add(2);
    aList.add(5);
    aList.add(8);

    System.out.println(aList.getLast());

    LinkedList<Integer> bList = new LinkedList<>();
    bList.add(1);
    bList.add(3);
    bList.add(7);
    bList.add(4);

    int k = bList.size() > aList.size() ? bList.size() : aList.size();
    System.out.println("list.size()=" + k);
    for (int i = 0; i < k; i++) {
      System.out.println(bList.getLast() + "," + aList.getLast());

    }


  }

}
