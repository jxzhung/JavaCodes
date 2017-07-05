package com.jzhung.util;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Jzhung on 2017/6/23.
 */
public class FileEncryptUtil {

    private static final int START = 1;
    private static final int KEY = 50;

    /**
     * 加密
     */
    public static void encrypt(String filePath){
        try {
            File file = new File(filePath);
            if(file.length() >= START + KEY) {
                RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
                raf.seek(START);
                byte[] bytes = new byte[KEY];
                raf.read(bytes, 0, bytes.length);
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) ~(bytes[i]);
                }
                raf.seek(START);
                raf.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密
     */
    public static void decrypt(String filePath){
        encrypt(filePath);
    }
}
