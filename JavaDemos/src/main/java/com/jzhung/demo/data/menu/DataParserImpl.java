package com.jzhung.demo.data.menu;

import com.jzhung.demo.data.menu.bean.Menu;
import com.jzhung.demo.data.menu.bean.MenuItem;
import com.jzhung.demo.data.menu.bean.MenuResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/** 解析文本
 * Created by Jzhung on 2017/5/2.
 */
public class DataParserImpl implements IDataParser {
    private static final int STEP_MENUTYPE = 3;
    private static final int STEP_MENUITEM = 4;
    private int step; //节点
    private int lineIndex; //行号

    private Pattern mMenuPtn = Pattern.compile("");//TODO: 2017/5/2 正则提取
    private Pattern mMenuItemPtn = Pattern.compile("");//TODO: 2017/5/2 正则提取
    public MenuResponse parse(String data) {
        MenuResponse resp = new MenuResponse();
        List<Menu> menuList = new ArrayList<Menu>();
        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
        resp.setMenuList(menuList);
        resp.setMenuItemList(menuItemList);

        StringTokenizer st = new StringTokenizer(data, "\n");
        resp.setStatus(st.nextToken()); //状态码
        resp.setTime(st.nextToken()); //时间

        while (st.hasMoreTokens()){
            String line = st.nextToken();
            if(line.equals("[menutype]")){
                step = STEP_MENUTYPE;
            }else if(line.equals("[menuitem]")){
                step = STEP_MENUITEM;
            }else {
                switch (step){
                    case STEP_MENUTYPE:
                        menuList.add(unboxMenu(line));
                        break;
                    case STEP_MENUITEM:
                        menuItemList.add(unboxMenuItem(line));
                        break;
                }
            }

            System.out.println(line);
            lineIndex ++;
        }
        return null;
    }

    /**
     * 将一行解析成一个MenuItem
     * @param line
     * @return
     */
    private MenuItem unboxMenuItem(String line) {

        return null;
    }

    /**
     * 将一行解析成一个Menu
     * @param line
     * @return
     */
    private Menu unboxMenu(String line) {
        return null;
    }
}
