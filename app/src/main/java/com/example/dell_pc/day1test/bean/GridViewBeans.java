package com.example.dell_pc.day1test.bean;

import android.graphics.drawable.Drawable;

public class GridViewBeans {
    private int image;
    private String text;

    public GridViewBeans(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GridViewBeans{" +
                "image=" + image +
                ", text='" + text + '\'' +
                '}';
    }
}
