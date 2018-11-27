package com.code.date;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestMain {
    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
//        String str = sdf.format(new Date());
//        System.out.println(str);
//
//        DateTime today = new DateTime();
//        DateTime datetorrow = today.plusDays(1);
//
//        System.out.println(today.toString("yyyy-MM-ddTHH:mm:ssZ"));//2017-06-26

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(sdf2.format(new Date()));
    }
}
