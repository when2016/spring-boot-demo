package com.code.jdk8.files;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//https://www.cnblogs.com/RealWorld/p/7640787.html
public class ReadTxt {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<User> list = initList();
        // jdk8之前的排序
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return String.valueOf(o1.getAge()).compareTo(String.valueOf(o2.getAge()));
            }
        });

        // jdk8 lambda排序，带参数类型
        list = initList();
        list.sort((User u1, User u2) -> String.valueOf(u1.getAge()).compareTo(String.valueOf(u2.getAge())));
        list.forEach(System.out::println);

        System.out.println();

        // jdk8 lambda排序，不带参数类型
        list = initList();
        list.sort((u1, u2) -> String.valueOf(u1.getAge()).compareTo(String.valueOf(u2.getAge())));
        list.forEach(System.out::println);

        System.out.println();

        // jdk8 升序排序，Comparator提供的静态方法
        list = initList();
        Collections.sort(list, Comparator.comparing(User::getAge));
        list.forEach(System.out::println);

        System.out.println();

        // jdk8 降序排序，Comparator提供的静态方法
        list = initList();
        Collections.sort(list, Comparator.comparing(User::getAge).reversed());
        list.forEach(System.out::println);

        System.out.println();

        // jdk8 组合排序，Comparator提供的静态方法，先按年纪排序，年纪相同的按名称排序
        list = initList();
        Collections.sort(list, Comparator.comparing(User::getAge).thenComparing(User::getName));
        list.forEach(System.out::println);
    }

    /**
     * 读入TXT文件
     *
     * @return
     * @throws IOException
     */
    private static List<User> initList() throws IOException {
        List<User> list = new ArrayList<>();
        String pathname = "E:\\test\\User.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = ""; // 每一行的内容
        int i = 1;
        while ((line = br.readLine()) != null) {
            String[] split = line.trim().split("\\s+");// .trim()可以去掉首尾多余的空格
            // .split("\\s+")表示用正则表达式去匹配切割,\\s+表示匹配一个或者以上的空白符
            if (i > 1) { // 第一行不要
                list.add(new User(split[0], Integer.valueOf(split[1]))); // 添加一个User实体
            }
            i++;
        }
        reader.close();
        br.close();
        return list;
    }
}
