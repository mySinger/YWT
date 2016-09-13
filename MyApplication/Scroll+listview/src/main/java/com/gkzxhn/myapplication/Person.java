package com.gkzxhn.myapplication;

/**
 * Created by ZengWenZhi on 2016/8/30 0030.
 */

public class Person {
    public int name;
    public String age;

    public Person(int name, String age) {
        this.name = name;
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
