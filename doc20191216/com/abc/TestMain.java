package com.ms.abc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class TestMain {
    public static void main(String[] args) {
        String a= "abcd.png";
        System.out.println(a);
        String name = StringUtils.substringBefore(a,".");
        System.out.println(name);

        String add = DateFormatUtils.format(1546410887183L,"yyyy-MM-dd");
        System.out.println(add);

    }
}
