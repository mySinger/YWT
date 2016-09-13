package com.gkzxhn.myapplication;

/**
 * Created by ZengWenZhi on 2016/8/29 0029.
 */

public class Goods {
    public String name;
    public String introduce;
    public String price;

    public Goods(String name, String introduce, String price) {
        this.name = name;
        this.introduce = introduce;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
