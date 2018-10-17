package com.code.jedis;

import com.code.jedis.util.JedisApi;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

//https://www.jianshu.com/p/125357ee7651
public class TestKey {
    public static void main(String[] args) {
        System.out.println("====key功能展示====");
        try {
            JedisApi jedisApi = new JedisApi();
            Jedis jedis = jedisApi.getRedis("127.0.0.1", 6379);
            //jedis.select(0);
            //System.out.println("清除数据：" + jedis.flushDB());
            System.out.println("判断某个键是否存在：" + jedis.exists("1"));
            System.out.println("新增{1，a}键值对:" + jedis.set("1", "a"));
            System.out.println(jedis.exists("1"));
            System.out.println("新增{2，b}键值对:" + jedis.set("2", "b"));
            System.out.println("系统中所有的键如下：" + jedis.keys("*").toString());
            System.out.println("删除键 1:" + jedis.del("1"));
            System.out.println("判断键 1是否存在：" + jedis.exists("1"));
            System.out.println("设置键 2的过期时间为5s:" + jedis.expire("2", 5));
            TimeUnit.SECONDS.sleep(2);
            System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
            System.out.println("移除键 2的生存时间：" + jedis.persist("2"));
            System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
            System.out.println("查看键 2所存储的值的类型：" + jedis.type("2"));
            System.out.println("查看键 2的值：" + jedis.get("2"));

            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
