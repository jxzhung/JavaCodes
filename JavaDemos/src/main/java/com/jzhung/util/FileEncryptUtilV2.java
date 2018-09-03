package com.jzhung.util;

import java.io.File;
import java.io.FileFilter;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by Jzhung on 2017/6/23.
 */
public class FileEncryptUtilV2 {
    private static final int START = 1;
    private static final int KEY = 50;

    public static void main(String[] args) {
        String dir = "F:\\Data\\test";
        long start = System.currentTimeMillis();
        encryptDir(dir, ".txt");
        long time = System.currentTimeMillis() - start;
        System.out.println("时间：" + time);
        //decryptDir(dir);
    }

    public static void encryptDir(String dir, final String fileext){
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isFile() && file.getName().endsWith(fileext)){
                    return true;
                }
                return false;
            }
        });
        for (File file : files) {
            encryptFile(file);
            String name = file.getName();
            String noextName = name.substring(0, name.indexOf("."));
            String newFile = noextName + "-" + UUID.randomUUID().toString().substring(26).replace("-", "");
            File saveFile = new File(file.getParentFile(), newFile);
            boolean result = file.renameTo(saveFile);
            System.out.println(file.getAbsolutePath() +  " -> " + saveFile.getName() + " " + result);
        }
    }

    private static void decryptDir(String dir) {
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isFile() && !file.getName().endsWith(".mp4")){
                    return true;
                }
                return false;
            }
        });
        for (File file : files) {
            //再执行一次进行解码
            encryptFile(file);
            String name = file.getName();
            String shortName = name.substring(0, name.indexOf("-"));
            String newFile = shortName + ".mp4";
            File saveFile = new File(file.getParentFile(), newFile);
            boolean result = file.renameTo(saveFile);
            file.renameTo(saveFile);

            System.out.println(file.getAbsolutePath() +  " -> " + saveFile.getName() + " " + result);
        }
    }

    private static void encryptFile(File file) {
        try {
            if(file.length() >= START + KEY) {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
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
}
