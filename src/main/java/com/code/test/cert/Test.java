package com.code.test.cert;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        String cPath = "/home/work/feng/audio/process";
        String url = "http://totoro.totoro.cdn.shandianshua.com/628186a7dc04acc322d80d10be99bd9f.wav";
        System.out.println(url.replaceAll("^.+/([^/]+)$", "$1"));
        String filePath = cPath + "/" + url.replaceAll("^.+/([^/]+)$", "$1");
        System.out.println(filePath);

        //url.replaceAll("^.+/([^/]+)$","$1");

        String file = "E://test//abc.silk";
        String fileTmp = file.replace(".silk",".wav");
        System.out.println(fileTmp);
        System.out.println(fileTmp.matches("^.+\\.wav$"));
        System.out.println(file.matches("^.+\\.silk$"));
        if(new File(fileTmp).exists()) {
            System.out.println(".wav存在");
        }
        if (!new File(file.replace(".silk", ".wav")).exists()) {
            System.out.println("处理失败");
        }

        System.out.println(filePath);
        System.out.println(filePath);
    }

}
