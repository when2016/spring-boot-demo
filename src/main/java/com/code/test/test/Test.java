package com.code.test.test;

import com.code.test.utils.TemplateMsg;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author wanghongen
 * @date 8/24/18 2:04 PM
 */
public class Test {


    public static void main(String[] args) {

        String aa = "abcd.aac";
        if(aa.endsWith(".silk") || aa.endsWith(".aac")) {
            System.out.println(aa);
        }

        System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd 10:30"));
        System.out.println(System.currentTimeMillis());

//        1539422183708
//        1539422290446
//        1539423804268

        LocalDate now = LocalDate.now();// 获得当前日期
        System.out.println("当前日期:"+now.toString());
        System.out.println("当前日期加一天:"+now.plusDays(1).toString());
        System.out.println("当前日期减一天:"+now.minusDays(1).toString());
        System.out.println("当前日期加一周:"+now.plusWeeks(1));
        System.out.println("当前日期减一周:"+now.minusWeeks(1));
        System.out.println("今年的第100天是几月几号:" + now.withDayOfYear(52));

        System.out.println("当前日期减8天:"+now.minusDays(8).toString());

        Map<String, Object> data = Maps.newHashMap();
        data.put("keyword1", TemplateMsg.item("步数赚钱"));
        data.put("keyword2", TemplateMsg.item("xxxxxx"));
        data.put("keyword3", TemplateMsg.item("xxxxxxx"));
        data.put("keyword4", TemplateMsg.item(LocalDate.now().toString()));
        data.put("keyword5", TemplateMsg.item("虚拟物品无需物流"));
        data.put("keyword6", TemplateMsg.item("虚拟物品无需物流"));
        data.put("goodsId","xxxxx");

        String goodsId = (String) data.remove("goodsId");
        if(StringUtils.isNotBlank(goodsId)) {
            System.out.println(goodsId);
        }

    }
}
