package com.code.jedis;

import redis.clients.jedis.Jedis;

//https://blog.csdn.net/lcwben/article/details/54894000
public class TestStr {
    public static void main(String[] args) {
        Jedis jedisCli = new Jedis("127.0.0.1", 6379);
        jedisCli.set("firstJedis", "hello,Jedis");
        System.out.println(jedisCli.get("firstJedis"));
    }
}
