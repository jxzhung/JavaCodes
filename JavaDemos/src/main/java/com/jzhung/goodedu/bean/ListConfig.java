package com.jzhung.goodedu.bean;

import java.util.List;

/**
 * Created by Jzhung on 2018/3/5.
 */

public class ListConfig {
    private String version;
    private List<Item> itemList;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
