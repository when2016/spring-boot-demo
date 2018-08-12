package com.wanghongen.demo;

/**
 * Created by wang on 2018/6/26
 */
public class TestMain {

  public static void main(String[] args) {

    MyThread1 t1 = new MyThread1("A");
    MyThread1 t2 = new MyThread1("B");
    t1.start();
    t2.start();

    //测试Runnable
    MyThread2 t3 = new MyThread2();
    new Thread(t3).start();//同一个t1，如果在Thread中就不行，会报错
    new Thread(t3).start();
    new Thread(t3).start();

  }

}

class MyThread1 extends Thread {

  private String name;

  public MyThread1(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(name + ":" + i);
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class MyThread2 implements Runnable {

  private int ticket = 10;

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      if (this.ticket > 0) {
        System.out.println("卖票：ticket" + this.ticket--);
      }
    }
  }
}
