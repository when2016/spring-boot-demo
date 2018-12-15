package com.ms.abc;

import org.apache.commons.lang3.StringUtils;

public class TestMain {
    public static void main(String[] args) {
        String a= "abcd.png";
        System.out.println(a);
        String name = StringUtils.substringBefore(a,".");
        System.out.println(name);
    }
}
