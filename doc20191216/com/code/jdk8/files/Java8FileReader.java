package com.code.jdk8.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Java8FileReader {
    public static void main(String[] args) throws IOException {
        // Java8用流的方式读文件，更加高效
        Files.lines(Paths.get("E:\\test\\User.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
    }

//---------------------
//    作者：资深架构师
//    来源：CSDN
//    原文：https://blog.csdn.net/chszs/article/details/44023039
//    版权声明：本文为博主原创文章，转载请附上博文链接！
}
