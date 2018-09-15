package com.code.test.test;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author wanghongen
 * @date 8/24/18 2:04 PM
 */
public class Test {


    public static void main(String[] args) {

        System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        //Current Date
        LocalDateTime today = LocalDateTime.now();
        System.out.println("当前日期时间 DateTime=" + today);
        //Current Date using LocalDate and LocalTime
        LocalDate today2 = LocalDate.now();
        System.out.println("当前日期时间=" + today2);

    }
}
