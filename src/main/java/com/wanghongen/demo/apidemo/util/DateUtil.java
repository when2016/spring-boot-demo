package com.wanghongen.demo.apidemo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    //获取当前时间
    public static Date getNowDate(){
        //处理时间，默认查询半年之内的工单就可以了
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    //获取当前时间 - N个年
    public static Date getNowDateMinusYear(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,- n);
        return cal.getTime();
    }

    //获取当前时间 + N个年
    public static Date getNowDateAddYear(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,+ n);
        return cal.getTime();
    }

    //获取当前时间 - N个月
    public static Date getNowDateMinusMonth(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,- n);
        return cal.getTime();
    }

    //获取当前时间 + N个月
    public static Date getNowDateAddMonth(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,+ n);
        return cal.getTime();
    }

    //获取当前时间 - N个周
    public static Date getNowDateMinusWeek(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR,- n);
        return cal.getTime();
    }

    //获取当前时间 + N个周
    public static Date getNowDateAddWeek(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR,+ n);
        return cal.getTime();
    }

    //获取当前时间 - N个天
    public static Date getNowDateMinusDay(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,- n);
        return cal.getTime();
    }

    //获取当前时间 + N个天
    public static Date getNowDateAddDay(Integer n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,+ n);
        return cal.getTime();
    }
    public static Integer getCurrentSecond(){
        Long ms = System.currentTimeMillis() / 1000;
        return ms.intValue();
    }
    /**
     * 判断参数的格式是否为“yyyyMM”格式的合法日期字符串
     * @param str
     * @return true/false
     */
    public static boolean isValidDate(String str) {
        try {
            if (str != null && !str.equals("")) {
                if (str.length() == 6) {
                    // 闰年标志
                    boolean isLeapYear = false;
                    String year = str.substring(0, 4);
                    String month = str.substring(4, 6);
                    int vYear = Integer.parseInt(year);
                    // 判断年份是否合法
                    if (vYear < 1900 || vYear > 2200) {
                        return false;
                    }
                    // 判断是否为闰年
                    if (vYear % 4 == 0 && vYear % 100 != 0 || vYear % 400 == 0) {
                        isLeapYear = true;
                    }
                    // 判断月份
                    // 1.判断月份
                    if (month.startsWith("0")) {
                        String units4Month = month.substring(1, 2);
                        int vUnits4Month = Integer.parseInt(units4Month);
                        if (vUnits4Month == 0) {
                            return false;
                        }
                    } else {
                        // 2.判断非0打头的月份是否合法
                        int vMonth = Integer.parseInt(month);
                        if (vMonth != 10 && vMonth != 11 && vMonth != 12) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + dateFormat.format(DateUtil.getNowDate()));
        System.out.println("减一年：" + dateFormat.format(DateUtil.getNowDateMinusYear(1)));
        System.out.println("减一月：" + dateFormat.format(DateUtil.getNowDateMinusMonth(1)));
        System.out.println("减一周：" + dateFormat.format(DateUtil.getNowDateMinusWeek(1)));
        System.out.println("减一天：" + dateFormat.format(DateUtil.getNowDateMinusDay(1)));
    }
}
