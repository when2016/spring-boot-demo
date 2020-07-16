package com.wanghongen.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HelloWorld {
    private String name;
    private String sex;

    public HelloWorld(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

}
