package com.code.test.thread;

import com.code.test.utils.TemplateEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//https://blog.csdn.net/u010940300/article/details/50251841/
public class Test {
    public static void main(String[] args) {
        System.out.println("开始。。。。。。。。。");
        for (int i = 1; i <= 10; i++) {
            Map map = new HashMap();
            map.put("keyword1", "data" + i);
            TemplateSendUtils.sendEvent("userId" + i, TemplateEnum.ORDER_DELIVERY, map);
        }
        System.out.println("结束。。。。。。。。。");

    }


}
