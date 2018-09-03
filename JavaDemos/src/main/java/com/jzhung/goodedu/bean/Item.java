package com.jzhung.goodedu.bean;

/**
 * Created by Jzhung on 2018/3/5.
 */

public class Item {
    private String name;
    private String data;

    public Item(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
