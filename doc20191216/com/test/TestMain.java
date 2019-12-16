package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestMain {
    public static void main(String[] args) {

        //group=app,group=pc
        String group = "pc";
        if (StringUtils.isEmpty(group)) {
            //特工
            System.out.println(1);
        } else {
            if (StringUtils.equals(group, "pc")) {
                //标注
                System.out.println(2);
            } else {
                //特工
                System.out.println(3);
            }
        }

    }

    //取当天日期
    public static Long getToday24HourLong() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        long tt = calendar.getTime().getTime() / 1000;
        return tt;
    }
}
