package com.gkzxhn.myapplication;

/**
 * Created by ZengWenZhi on 2016/8/17 0017.
 */

public class News {
    private int img;
    private String content;

    public News() {
    }

    public News(String content, int img) {
        this.content = content;
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
