package com.jzhung.demo.other;

import java.io.File;

/**
 * Created by Jzhung on 2018/4/28.
 */
public class FileTest {
    public static void main(String[] args) {
        new FileTest().delFileOrDir(new File("F:\\Data\\新建文本文档.txt"));
    }

    private void delFileOrDir(File file) {
        if(file.isDirectory()){
            //递归删除
            File[] child = file.listFiles();
            for (int i = 0; i < child.length; i++) {
                File c = child[i];
                if(c.isDirectory()){
                    delFileOrDir(c);
                }else{
                    c.delete();
                    //Log.i(TAG, "删除文件:" + file.getAbsolutePath());
                    System.out.println( "删除文件：" + c.getAbsolutePath());
                }
            }
        }
        file.delete();
        //Log.i(TAG, "删除：" + file.getAbsolutePath());
        System.out.println( "删除2：" + file.getAbsolutePath());

    }
}
