package com.jzhung.demo.data.menu;

import com.jzhung.demo.data.menu.bean.MenuResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Jzhung on 2017/5/2.
 */
public class Main {
    public static void main(String[] args) {
        testParse();
    }

    private static void testParse() {
        IDataParser parser = new DataParserImpl();
        String data = readData();
        //System.out.println(data);
        MenuResponse response = parser.parse(data);
    }

    private static String readData() {
        String dataFile = "d:\\menus_offline_utf8.asp.txt";
        StringBuilder builder = new StringBuilder();
        try {
            String encoding="utf-8";
            File file = new File(dataFile);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    builder.append(lineTxt);
                    builder.append("\n");
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return builder.toString();
    }
}
