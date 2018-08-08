package com.wanghongen.demo.test;

import java.util.Stack;

/**
 * @author wanghongen
 * @date 7/31/18 7:48 PM
 */
public class Test7 {

  public static Stack<Integer> stack1 = new Stack<>();
  public static Stack<Integer> stack2 = new Stack<>();
  public static void push(int node) {
    stack1.push(node);
  }

  public static int pop() {
    if(stack2.empty()) {
      while (!stack1.empty()) {
        stack2.push(stack1.pop());
      }
    }

    if(stack2.empty()) {
      System.out.println("queue is empty");
    }
    return stack2.pop();
  }

  public static void main(String[] args) {
    push(1);
    push(2);
    push(3);
    push(4);

    System.out.println(pop());
    System.out.println(pop());
    System.out.println(pop());
    push(5);
    push(6);
    System.out.println(pop());
    System.out.println(pop());
    System.out.println(pop());

  }

}
