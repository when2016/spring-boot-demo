package com.code.test.cert;

import java.io.File;

public class TestMain {

    public static void main(String[] args) {
        String filePath = "e://test/abc.aac";
        System.out.println(filePath);
        if (!new File(filePath.replace(".silk", ".wav")).exists()
                || !new File(filePath.replace(".aac", ".wav")).exists()) {
            System.out.println("不存在-----------silk");
        }else{
            System.out.println("存在.wav----------.silk");
        }
        if (!new File(filePath.replace(".aac", ".wav")).exists()) {
            System.out.println("不存在----------aac");
        }else{
            System.out.println("存在.wav---------aac");
        }

        String a = filePath.replace(".aac",".wav");
        a="e://test/abc.aac";
        System.out.println("a==="+a);



        if(a.endsWith(".silk") || a.endsWith(".aac")) {
            System.out.println("type=1");
        }

        String proFilePath=filePath;
        if(filePath.endsWith(".silk")) {
            proFilePath = filePath.replace("silk",".wav");
        }else if(filePath.endsWith(".aac")) {
            proFilePath = filePath.replace(".aac",".wav");
        }

        if(!new File(proFilePath).exists()){
            System.out.println("不存在");
        }else{
            System.out.println("存在");
        }

    }

}
