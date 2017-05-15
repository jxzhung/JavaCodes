package com.jzhung.demo.data.menu;

import com.jzhung.demo.data.menu.bean.MenuResponse;

/** 数据解析接口
 * Created by Jzhung on 2017/5/2.
 */
public interface IDataParser {
    /**
     * 解析数据返回菜单响应对象
     * @param data
     * @return
     */
    MenuResponse parse(String data);
}
