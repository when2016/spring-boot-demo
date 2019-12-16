package com.code.test.test;

import com.alibaba.fastjson.JSONObject;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) throws Exception {

//        String outDir = "d:/tmp2/tf_1_6";
//        String content = "bbbbbb";
//        Path path = Paths.get(outDir);
//        if (!Files.exists(path)) Files.createDirectories(path);
//        Files.write(Paths.get(path + "/log.txt"), content.getBytes());
//        ;
//
//        Path logPath = Paths.get(outDir);
//        if (!Files.exists(logPath)) Files.createDirectories(logPath);
//
//        System.out.println(logPath.getRoot());
//        System.out.println(logPath.getFileName());
//        FileOutputStream outAndErr = new FileOutputStream(outDir + "/abc.txt");
//        outAndErr.write("ssssssssssss".getBytes());
//        outAndErr.close();

//        int pcnt = "/bedroom sets/".split("/").length;
//        System.out.println(pcnt);
//        int i = 0;
//        System.out.println(i++);
//        System.out.println(i++);

        StringBuilder sb = new StringBuilder();
        int length = 3;
        for (int i = 0; i < length; i++) {
            String dirs = "cat01;cat02;cat03;dog01;dog02;dog03";
            String ossFs = "/data/";
            String dsetId = i + "";

            String str = Stream.of(dirs.split(";")).map(s -> ossFs + "dsets/" + dsetId + "/" + s).collect(Collectors.joining(":", "", ""));
            sb.append(str);
            if (i < length - 1) {
                sb.append(":");
            }
            System.out.println(str);
        }
        System.out.println(sb.toString());


        String imagePath = "/data/check/94_1/n01484850_3479_1559701183174.JPEG";
        // 正则表达式“.+/(.+)$”的含义就是：被匹配的字符串以任意字符序列开始，后边紧跟着字符“/”，
        // 最后以任意字符序列结尾，“()”代表分组操作，这里就是把文件名做为分组，匹配完毕我们就可以通过Matcher
        // 类的group方法取到我们所定义的分组了。需要注意的这里的分组的索引值是从1开始的，所以取第一个分组的方法是m.group(1)而不是m.group(0)。
        String logTxt = "log.txt";
        Pattern p = Pattern.compile(".+/(.+)$");
        Matcher m = p.matcher(imagePath);
        if (m.find()) {
            String fileName = m.group(1);
            logTxt = fileName.substring(0, fileName.lastIndexOf(".")) + ".txt";
            System.out.println(logTxt);
        }


    }
}
