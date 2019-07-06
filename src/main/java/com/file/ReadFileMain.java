package com.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadFileMain {
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        //2.读执行日志，返回日志结果
        File logFile = new File("E:/springcloud/yundata-service-ai/log.txt");
        List<String> logList = FileUtils.readLines(logFile, "utf-8");

        //INFO:tensorflow:Final test accuracy top1 = 100.0% (N=17)
        //INFO:tensorflow:Final test accuracy top5 = 100.0% (N=17)


        Set<String> set = new HashSet<>();
        StringBuilder resultSb = new StringBuilder();
        for (int i = logList.size() - 1; i > 0; i--) {
            String line = logList.get(i);
            System.out.println(line);
            if (!StringUtils.contains(line, "Final test accuracy top"))
                continue;
            String[] strArr = line.split(" ");
            if (strArr.length > 5) {
                resultSb.append(strArr[3]).append("准确率").append(strArr[5]).append(",");
                set.add(strArr[3]);
            }
            if (set.contains("top1") && set.contains("top5")) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " 毫秒 ");
        System.out.println(resultSb.toString());
    }
}
