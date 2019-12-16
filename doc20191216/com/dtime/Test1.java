package com.dtime;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test1 {

    public static void main(String[] args) throws Exception {
        String day = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        day = dateFormat.format(calendar.getTime());


        Calendar time = Calendar.getInstance();
        time.setTime(dateFormat.parse(day));
        long stime = time.getTimeInMillis() / 1000;
        time.add(Calendar.DAY_OF_MONTH, 1);
        long etime = time.getTimeInMillis() / 1000;

        JSONObject queryParams = new JSONObject();
        queryParams.put("stime", stime);
        queryParams.put("etime", etime);
        queryParams.put("day", day);

        System.out.println(stime);
        System.out.println(etime);
        System.out.println(day);

    }

}
