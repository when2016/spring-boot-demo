package com.code.test.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Map> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Map map = new HashMap();
            map.put("id", i + "");
            map.put("name", "name" + i);
            map.put("code", "code");
            list.add(map);
        }
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        System.out.println("----------------------list.size()=" + list.size());
        List<Map> mapList = list.stream().filter(map -> !set.contains(map.getOrDefault("id", ""))).collect(Collectors.toList());
        System.out.println("----------------------list.size()=" + list.size());
        mapList.stream().forEach(map -> System.out.println("id=" + map.get("id") + ",name=" + map.get("name")));


        System.out.println("---------------------------");

        Set<Integer> posSet = new HashSet<>();
        List<Map> data = new ArrayList<>();
        Random random = new Random();
        int count = mapList.size() > 5 ? 5 : mapList.size();
        System.out.println("---------------------------===count=" + count);
        int pos = 0;
        for (int i = 0; i < count; i++) {
            pos = random.nextInt(mapList.size());
            System.out.println("pos================" + pos);
            if (!posSet.contains(pos)) {
                posSet.add(pos);
                Map m = mapList.get(pos);
                System.out.println("pos=" + pos + "," + m.get("id") + "," + m.get("name") + ",mapList.size=" + mapList.size());
                String id = m.get("id").toString();

                data.add(m);

            } else {
                i--;
            }
        }

        System.out.println("----------------------data.size()=" + data.size());
        data.stream().forEach(map -> System.out.println(map.get("id") + "," + map.get("name")));
    }
//
//    public static List getRandomList(List paramList, int count) {
//        if (paramList.size() < count) {
//            return paramList;
//        }
//        Random random = new Random();
//        List<Integer> tempList = new ArrayList<Integer>();
//        List<Object> newList = new ArrayList<Object>();
//        int temp = 0;
//        for (int i = 0; i < count; i++) {
//            temp = random.nextInt(paramList.size());//将产生的随机数作为被抽list的索引
//            if (!tempList.contains(temp)) {
//                tempList.add(temp);
//                newList.add(paramList.get(temp));
//            } else {
//                i--;
//            }
//        }
//        return newList;
//    }
}
