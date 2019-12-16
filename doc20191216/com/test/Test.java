package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {

//        System.out.println(getFirstDayOfMonth());
//        System.out.println(getTodayDate());

//        String origin="http://manager.longmaosoft.com";
//        String agent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36";
//        String state = DigestUtils.md5Hex(origin + agent);
//        System.out.println(state);

//        long days = (1555430400l / 3600 / 24 - 1554998400l / 3600 / 24) + 1;
//        System.out.println(days);


        long etime = new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-05").getTime() / 1000 + 3600 * 24;
        System.out.println(etime);

    }

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static long parseSecond(String date) {
        return LocalDateTime.parse(date, format).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static String getDateStr(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

    public static String getFormatDefaultDate(String date) {
        if (StringUtils.isEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(new Date(date));
        }
        return date;
    }

    public static String getFirstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDayOfMonth = format.format(cal_1.getTime());
        return firstDayOfMonth;
    }

    public static String getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        return format.format(cal_1.getTime());
    }

}
