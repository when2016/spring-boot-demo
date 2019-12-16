package com.code.jedis;

import redis.clients.jedis.Jedis;

//https://blog.csdn.net/lcwben/article/details/54894000
public class TestList {
    public static void main(String[] args) {
        Jedis jedisCli = new Jedis("127.0.0.1", 6379);
        jedisCli.lpush("Countries","USA");
        jedisCli.lpush("Countries","UK");
        jedisCli.lpush("Countries","CHINA");
        System.out.println(jedisCli.rpop("Countries"));
    }
}
