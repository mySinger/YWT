package com.example.mysqlite;

import static android.R.attr.id;

/**
 * Created by ZengWenZhi on 2016/9/8 0008.
 */
public class User {
    public User() {
    }

    public User(int imgId, String name, int age ) {

        this.name = name;
        this.age = age;
        this.imgId = imgId;
    }

    /**
     * 图片id
     */
    private int imgId;

    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", imgId=" + imgId + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getImgId() {return imgId;}

    public void setImgId(int imgId) {this.imgId = imgId;}
}

