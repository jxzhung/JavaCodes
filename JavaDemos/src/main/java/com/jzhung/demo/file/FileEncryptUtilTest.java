package com.jzhung.demo.file;

import java.io.File;
import java.util.UUID;

/**
 * Created by Jzhung on 2017/10/18.
 */
public class FileEncryptUtilTest {
    public static void main(String[] args) {
        String file = "D:\\app.txt";
        FileEncryptUtil.encrypt(file);
    }

    public static void createStatusFile(String filePath){
        File file = new File(filePath);
        String replace = UUID.randomUUID().toString().replace("-", "");
        File ststusFile = new File(file.getParentFile(), replace);

    }
}
