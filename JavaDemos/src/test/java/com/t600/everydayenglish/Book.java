package com.t600.everydayenglish;

import java.util.List;

/**
 * Created by Jzhung on 2018/6/13.
 */
public class Book {
    String name;
    String url;
    List<Unit> unitList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
