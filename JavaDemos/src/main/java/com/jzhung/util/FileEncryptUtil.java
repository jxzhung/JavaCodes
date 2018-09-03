package com.jzhung.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Jzhung on 2017/6/23.
 */
public class FileEncryptUtil {

    private static final int START = 1;
    private static final int KEY = 2000000;

    public static void main(String[] args) {
        encrypt("d:\\1.mp3");
    }

    /**
     * 加密
     */
    public static void encrypt(String filePath) {
        Logger.i("加密文件：" + filePath);
        File file = new File(filePath);
        File statusFile = new File(filePath + ".ex");
        if (statusFile.exists()) {
            Logger.i("文件已加密(跳过)：" + filePath);
            return;
        }
        changeFile(file);
        try {
            statusFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解密
     */
    public static void decrypt(String filePath) {
        Logger.i("解密文件：" + filePath);
        File file = new File(filePath);
        File statusFile = new File(filePath + ".ex");
        if (!statusFile.exists()) {
            Logger.i("文件已解密(跳过)：" + filePath);
            return;
        }
        changeFile(file);
        statusFile.delete();
    }

    private static void changeFile(File file) {
        if (file.length() >= START + KEY) {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(file, "rw");
                raf.seek(START);
                byte[] bytes = new byte[KEY];
                raf.read(bytes, 0, bytes.length);
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) ~(bytes[i]);
                }
                raf.seek(START);
                raf.write(bytes);
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
