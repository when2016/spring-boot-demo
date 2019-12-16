package com.code.test.collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class ListSort {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        set.add("17");

        List<Map> list = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            Map map = new HashMap();
            map.put("id", i + "");
            map.put("name", "name" + i);
            map.put("code", "code");
            if (set.contains(i + "")) {
                map.put("status", 1);
            }
            System.out.println(map.get("status"));
            list.add(map);
        }


        list.sort(Comparator.comparingInt(a -> NumberUtils.toInt(Objects.toString(a.get("status")))));
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            System.out.println(map.get("id") + "," + map.get("name"));
        }



    }
}
