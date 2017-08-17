package com.example.ll.mvp.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public class TestBean implements Serializable {
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
