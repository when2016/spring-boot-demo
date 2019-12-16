package com.code.jedis;

import com.code.jedis.util.JedisApi;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class TestString {
    public static void main(String[] args) {
        try {
            JedisApi jedisApi = new JedisApi();
            Jedis jedis = jedisApi.getRedis("127.0.0.1", 6379);
            jedis.select(1);
            System.out.println("清除数据：" + jedis.flushDB());
            System.out.println("====字符串功能展示====");
            System.out.println("增:");
            System.out.println(jedis.set("a", "1"));
            System.out.println(jedis.set("b", "2"));
            System.out.println(jedis.set("c", "3"));
            System.out.println("删除键 a:" + jedis.del("a"));
            System.out.println("获取键 a:" + jedis.get("a"));
            System.out.println("修改键 b:" + jedis.set("b", "bChanged"));
            System.out.println("获取键 b 的值:" + jedis.get("b"));
            System.out.println("在键 c后面加入值：" + jedis.append("c", "End"));
            System.out.println("获取键 c的值：" + jedis.get("c"));
            System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
            System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
            System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
            System.out.println("删除多个键值对：" + jedis.del(new String[]{"key01", "key02"}));
            System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));

            jedis.flushDB();
            System.out.println("新增键值对防止覆盖原先值:");
            System.out.println(jedis.setnx("key001", "value001"));
            System.out.println(jedis.setnx("key002", "value002"));
            System.out.println(jedis.setnx("key002", "value002-new"));
            System.out.println("获取键key001的值：" + jedis.get("key001"));
            System.out.println("获取键key002的值：" + jedis.get("key002"));

            System.out.println("新增键值对并设置有效时间:");
            System.out.println(jedis.setex("key003", 2, "value003"));
            System.out.println("获取键key003的值：" + jedis.get("key003"));
            TimeUnit.SECONDS.sleep(3);
            System.out.println("获取键key003的值：" + jedis.get("key003"));

            System.out.println("获取原值，更新为新值:");
            System.out.println(jedis.getSet("key002", "key2GetSet"));
            System.out.println("获取键key002的值：" + jedis.get("key002"));

            System.out.println("截取key002的值的字符串：" + jedis.getrange("key002", 2, 5));

            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
