package com.code.date;

import java.text.SimpleDateFormat;

public class DateFormatUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Long getStrDate2TimeMills(String strDate) throws Exception {
        return dateFormat.parse(strDate).getTime() / 1000;
    }

    public static Long getStrDate2TimeMills(String strDate, String format) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(strDate).getTime() / 1000;
    }
}
