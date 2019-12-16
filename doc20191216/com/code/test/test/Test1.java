package com.code.test.test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {


    public static void main(String[] args) {
        Map<String, String> item = new HashMap<>();
        Long id = 102108L;
        String value = "http://static.cdn.longmaosoft.com/102108/865613/qmwdLu2v1CR4BMapp3n8HsKo3Kbqn23i.silk";
        System.out.println(value);

        String url = value.replaceAll(".silk", ".wav");
        System.out.println(url);
        item.put("value", url);

        System.out.println(item.get("value"));

        String value2 = "http://static.cdn.longmaosoft.com/1/2/qmwdLu2v1CR4BMapp3n8HsKo3Kbqn23i.acc";
        System.out.println(value2);
        String url2 = value2.replaceAll(".acc", ".wav");
        System.out.println(url2);
    }


}
