package com.file;

import java.io.File;

public class TestFile01 {
    public static void main(String[] args) {
        File file = new File("D:/BugReport.txt");
        if (file.exists()) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }
}
