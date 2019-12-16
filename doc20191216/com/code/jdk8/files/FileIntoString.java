package com.code.jdk8.files;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class FileIntoString {
    public static void main(String[] args) throws IOException {
        // 一行代码搞定读文件，默认是UTF-8编码
        System.out.println(new String(readAllBytes(get("E:\\test\\User.txt"))));
    }

//---------------------
//    作者：资深架构师
//    来源：CSDN
//    原文：https://blog.csdn.net/chszs/article/details/44023039
//    版权声明：本文为博主原创文章，转载请附上博文链接！
}
