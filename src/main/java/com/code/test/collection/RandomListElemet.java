package com.code.test.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomListElemet {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        List rsList = randomListElement(list, 2);
        rsList.forEach(s -> {
            System.out.println(s);
        });
    }

    private static List<String> randomListElement(List<String> list, int i) {
        List<String> randomList = new ArrayList<String>();
        //随机打乱list
        Collections.shuffle(list);
        for (int j = 0; j < i; j++) {
            randomList.add(list.get(j));
        }
        return randomList;
    }
}
