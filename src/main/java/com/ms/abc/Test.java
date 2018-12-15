package com.ms.abc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a=" + a);
        System.out.println("b=" + b);

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        int a1 = 1;
        int a2 = 2;
        int a3 = 4;
        int a4 = 8;
        int a5 = 16;
        System.out.println(31 & 1);
        System.out.println(31 & 4);
        System.out.println(31 & 16);
        System.out.println(4 & 1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(simpleDateFormat.format(System.currentTimeMillis()));

        System.out.println(Math.min(6, 3));

        System.out.println("-----------------");
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        Map<String, String> map = new HashMap<>();
        List<Map> listN = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + "");
            ///map.clear();
            map.put("uid", list.get(i) + "");
            listN.add(map);
        }
        listN.forEach(map1 -> System.out.println(map1.get("uid")));

    }
}
