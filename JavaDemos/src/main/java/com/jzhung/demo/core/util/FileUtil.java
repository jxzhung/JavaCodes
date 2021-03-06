package com.jzhung.demo.core.util;

import java.io.*;

/**
 * Created by Jzhung on 2016/11/28.
 */
public class FileUtil {
    public static String getFileContent(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public static void saveToFile(String filePath, String content) {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(content.getBytes("utf-8"));
            fout.flush();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
