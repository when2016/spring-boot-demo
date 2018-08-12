package com.wanghongen.demo.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghongen
 * @date 7/31/18 7:58 PM
 */
public class Test7_2 {

  public static Queue<Integer> queue1 = new LinkedList<Integer>();
  public static Queue<Integer> queue2 = new LinkedList<Integer>();

  public static void push(int e) {
    //插入非空队列
    if (queue2.isEmpty()) {
      queue1.offer(e);
    }
    if (queue1.isEmpty()) {
      queue2.offer(e);
    }
  }

  public static int pop() {
    //将非空队列的元素逐个出队并插入到空队列
    //poll,获取并移除此队列的头，如果此队列为空，则返回 null。
    //remove,获取并移除此队列的头。此方法与 poll 唯一的不同在于：此队列为空时将抛出一个异常。
    if (!queue1.isEmpty()) {
      while (queue1.size() > 1) {
        queue2.offer(queue1.poll());
      }
      return queue1.poll();
    } else if (!queue2.isEmpty()) {
      while (queue2.size() > 1) {
        queue1.offer(queue2.poll());
      }
      return queue2.poll();
    }
    return 0;

  }

  public static void main(String[] args) {
    push(1);
    push(2);
    push(3);
    push(4);
    push(5);
    System.out.println(pop());
  }
}
