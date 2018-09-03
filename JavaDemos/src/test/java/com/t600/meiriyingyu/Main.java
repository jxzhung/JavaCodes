package com.t600.meiriyingyu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzhung.demo.core.util.FileUtil;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2018/6/15.
 */
public class Main {
    private List<String> jsonFileList;
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        jsonFileList = new ArrayList<String>();
        jsonFileList.add("E:\\Data\\meiriyingyu\\6上.json");
        jsonFileList.add("E:\\Data\\meiriyingyu\\6下.json");

        String saveDir = "E:\\Data\\meiriyingyu";

        Gson gson = new Gson();
        Type type = new TypeToken<List<ListResp>>() {
        }.getType();
        for (String jsonFile : jsonFileList) {
            String json = FileUtil.getFileContent(jsonFile);
            List<ListResp> list = gson.fromJson(json, type);
            System.out.println("总量：" + list.size());
            for (int i = 0; i < list.size(); i++) {
                ListResp listResp = list.get(i);
                String mp3lrc = listResp.getMp3lrc().trim();
                String mp3url = listResp.getMp3url().trim();
                String title = listResp.getTitle().replace("?", "");

                if(mp3lrc.length() <20){
                    continue;
                }

                File lrcFile;
                File mp3File;
                if(jsonFile.contains("6上")){
                    lrcFile = new File(saveDir, "六上-" + title + ".lrc");
                    mp3File = new File(saveDir,  "六上-" + title + ".mp3");
                }else {
                    lrcFile = new File(saveDir,  "六下-" + title + ".lrc");
                    mp3File = new File(saveDir,  "六下-" + title + ".mp3");
                }
                if(lrcFile.exists() && mp3File.exists()){
                    continue;
                }

                try {
                    System.out.println("URL:" + mp3lrc);
                    Request.Get(mp3lrc).execute().saveContent(lrcFile);
                    System.out.println(mp3url);
                    Request.Get(mp3url).execute().saveContent(mp3File);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
