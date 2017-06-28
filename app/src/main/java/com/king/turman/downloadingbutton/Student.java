package com.king.turman.downloadingbutton;

import java.io.Serializable;

/**
 * Created by diaoqf on 2017/6/28.
 */

public class Student implements Serializable {

    private static final long serialVersionUID = 12312321312321321L;



    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
