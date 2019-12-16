package com.code.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

//https://blog.csdn.net/lcwben/article/details/54894000
public class TestMap {
    public static void main(String[] args) {
        Jedis jedisCli = new Jedis("127.0.0.1", 6379);
        jedisCli.hset("family", "lbq", "65");
        jedisCli.hset("family", "zjz", "62");
        System.out.println(jedisCli.hmget("family", "lbq", "zjz"));

        Map testMap1 = new HashMap<>();
        testMap1.put("num1", "1");
        testMap1.put("num2", "2");
        testMap1.put("num3", "606");
        testMap1.put("num4", "1024");
        jedisCli.hmset("testMap1", testMap1);
        System.out.println(jedisCli.hmget("testMap1","num1","num2","num3","num4"));


    }
}
