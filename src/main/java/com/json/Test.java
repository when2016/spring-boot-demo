package com.json;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {

        String aa = "/lm/aa/";
        System.out.println(aa.split("/").length);
        System.out.println(System.currentTimeMillis() / 1000);

        String cc = underLineToCamel("abc_efg");
        System.out.println(cc);

        int userRate = 3012 * 100 / 3129;
        System.out.println(userRate);

        StringBuffer sb = new StringBuffer();
        sb.append("abcddddddddddddddddddddddddd");
        System.out.println(sb.toString());
        sb = new StringBuffer();
        System.out.println(sb.toString());
        sb.append("fffffffffffffffffffffffffffff");
        System.out.println(sb.toString());

    }


    private static String underLineToCamel(String str) {
        Matcher matcher = Pattern.compile("_(\\w)").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}


