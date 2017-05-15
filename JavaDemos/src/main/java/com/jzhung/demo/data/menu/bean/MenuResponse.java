package com.jzhung.demo.data.menu.bean;

import java.util.List;

/** 响应对象
 * Created by Jzhung on 2017/5/2.
 */
public class MenuResponse {
    private String status;
    private String time;
    private List<Menu> menuList;
    private List<MenuItem> menuItemList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
