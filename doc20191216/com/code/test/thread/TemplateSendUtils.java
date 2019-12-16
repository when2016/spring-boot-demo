package com.code.test.thread;

import com.code.test.utils.TemplateEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TemplateSendUtils {
    //https://blog.csdn.net/u010940300/article/details/50251841/
    public static void sendEvent(String userId, TemplateEnum templateEnum, Map data) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {

            System.out.println(Thread.currentThread().getName() + "," + userId);
            System.out.println(Thread.currentThread().getName() + "," + templateEnum.getName());
            System.out.println(Thread.currentThread().getName() + "," + data.get("keyword1"));

            if (StringUtils.equals(templateEnum.name(), TemplateEnum.ORDER_DELIVERY.name())
                    || StringUtils.equals(templateEnum.name(), TemplateEnum.FRIEND_HELP.name())) {

                System.out.println("AAAAAAAAAAAAAAAAAA");
            }

            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "," + userId + "------=" + i);
            }
        });

        executor.shutdown();
    }
}
