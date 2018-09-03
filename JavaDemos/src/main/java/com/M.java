package com;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by Jzhung on 2017/9/21.
 */
public class M {
    public static void main(String[] args) throws UnsupportedEncodingException {
        test1();
        //test2();
    }

    private static void test2() {
        String dir1 = "/home/800G-ziyuanku/old_demo_two";
    }

    private static void test1() {
        String dir = "/home/800G-ziyuanku/old_demo_two";
        Scanner input = new Scanner(System.in);
        String filePath;
        while (true){
            System.out.println("input file path:");
            filePath = input.nextLine();
            //filePath = new String(filePath.getBytes("gbk"), "utf-8");
            System.out.println("filePath: " + filePath);

            String fullPath = dir + filePath;
            File file = new File(fullPath);
            System.out.println("FILE: " + file.getAbsolutePath());
            System.out.println(file.exists());
        }
    }
}
