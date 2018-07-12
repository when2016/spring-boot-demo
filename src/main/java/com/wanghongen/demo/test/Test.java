package com.wanghongen.demo.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2018/7/4
 */
public class Test {

  public static void main(String[] args) {
    Singleton.INSTANCE.whateverMethod();
<<<<<<< HEAD

    System.out.println(Math.round(11.5));
    System.out.println(Math.round(-11.5));

=======
    Map map = Collections.synchronizedMap(new HashMap<>());
>>>>>>> 71ac1ff87c15abc821f1c33f02b98d8f1baacdba
  }

}
