package com.jzhung.demo.file;

import com.jzhung.demo.core.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2018/6/21.
 */
public class DouYinUtil {

    @Test
    public void testGetFileIds() {
       /* String dir = "I:\\Video\\DouYin";
        List<String> fileIds = getFileIds(dir);
        System.out.println("------------");
        for (String fileId : fileIds) {
            System.out.println(fileId);
        }*/

        getFileIdsFromFile("F:\\douyin.txt");
    }

    public List<String> getFileIds(String dir) {
        List<String> list = new ArrayList<String>();

        File file = new File(dir);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.getName().endsWith(".mp4")) {
                        return true;
                    }
                    return false;
                }
            });

            for (File cur : files) {
                String name = cur.getName();
                String id = name.substring(name.lastIndexOf(" ") + 1, name.lastIndexOf("."));
                System.out.println(id);
                list.add(id);
            }
        }

        return list;
    }

    public List<String> getFileIdsFromFile(String filePath) {
        List<String> list = new ArrayList<String>();

        File file = new File(filePath);
        if (file.exists()) {
            String content = FileUtil.getFileContent(filePath);
            StringTokenizer st = new StringTokenizer(content, "\n");
            while (st.hasMoreTokens()){
                String name = st.nextToken();
                String id = name.substring(name.lastIndexOf(" ") + 1, name.lastIndexOf("."));
                System.out.println(id);
                list.add(id);
            }
        }

        return list;
    }
}
