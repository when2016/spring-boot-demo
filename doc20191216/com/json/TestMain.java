package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TestMain {
    public static void main(String[] args) {
//
//        JSONObject data = JSONObject.parseObject("{\"snum\":20,\"type\":\"file\",\"list\":[85309,85307,85306,85305,85304],\"sid\":37}");
//
//        if (StringUtils.equals(data.getString("type"), "file")) {
//            JSONArray array = data.getJSONArray("list");
//            List<String> spotCheckDbList = new ArrayList<>();
//            for (int i = 0; i < array.size(); i++) {
//                System.out.println(array.get(i));
//                spotCheckDbList.add("" + array.get(i));
//            }
//            //Collections.shuffle(spotCheckDbList);
//            System.out.println("-------------------");
//            List<String> tmp = new ArrayList<>();
//            tmp.add("a");
//            tmp.add("b");
//            spotCheckDbList.addAll(0, tmp);
//            for (int i = 0; i < spotCheckDbList.size(); i++) {
//                System.out.println(spotCheckDbList.get(i));
//            }
//        }

//        //1,2,4,8
//        System.out.println(1 + 2 + 4 + 8);
//        System.out.println(8 & 7);
//        System.out.println(1 & 6);
//
//        System.out.println("=============");
//        System.out.println(1&4);
//

        int  a = 1;
        int b =2;
        int c =4;
        int d = 8;

        int a1 = a+b;
        System.out.println(a1);

        System.out.println(4&a1);




    }
}
