package com.t600.util;

import com.jzhung.demo.core.util.FileUtil;

import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2017/9/15.
 */
public class SqlTxtUtil {
    public static void main(String[] args) {
        String path = "C:\\Users\\Jzhung\\Desktop\\word(2).txt";
        String fileContent = FileUtil.getFileContent(path);
        StringTokenizer st = new StringTokenizer(fileContent, "\n");
        String line = null;
        StringBuilder builder = new StringBuilder();
        while (st.hasMoreTokens()){
            line = st.nextToken();
            line = line.trim();
            int mark = line.indexOf("#");
            String word = line.substring(0, mark);
            System.out.println("w:" + word);
            // 看单词部分是否末尾有空格
            /*if(word.endsWith(" ")){
                word = word.substring(0, line.indexOf(" "));
            }*/
            word = word.trim();
            builder.append(word);
            builder.append(line.substring(mark));
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }
}
