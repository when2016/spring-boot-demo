package com.code.regex;

public class TestRegex {
    public static void main(String[] args) {

        String url = "http://totoro.totoro.cdn.shandianshua.com/628186a7dc04aac322d80d10be99bd9f.silk";

        String newUrl = url.replaceAll(".silk|.aac", ".wav");
        System.out.println("url=" + url);
        System.out.println("newUrl=" + newUrl);

    }
}
