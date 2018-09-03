package com.jzhung.demo.spider.aigei;

import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jzhung on 2017/8/17.
 */
public class AiGeiMain {
    public static void main(String[] args) {
        AiGeiMain aiGeiMain = new AiGeiMain();
        String url = "http://www.aigei.com/view/73196.html?order=name&page=";
        String saveDir = "F:\\Data\\帧图片";
        aiGeiMain.downloadImages(url, saveDir);
    }

    private void downloadImages(String url, String saveDir) {
        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            String dirName = title.substring(0, title.indexOf("-"));
            File saveDirFile = new File(saveDir, dirName);
            saveDirFile.mkdirs();

            //分页
            Element pageDiv = doc.getElementsByClass("blueFoot").first();
            Elements pageLinks = pageDiv.getElementsByTag("span");
            Element maxPageLink = pageLinks.get(pageLinks.size() - 2);
            int maxPage = Integer.valueOf(maxPageLink.text().trim());
            for (int i = 1; i <= maxPage; i++) {
                System.out.println("第"+ i + "页");
                String pageUrl = url + i;
                downOnePage(pageUrl, saveDirFile.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downOnePage(String pageUrl, String saveDir) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements boxes = doc.select(".unit-container-box");
            for (Element box : boxes) {
                Element title = box.select(".trans-title ").first();
                String name = title.text().trim();

                Element img = box.select("img").first();
                String url = img.attr("abs:src");

                File realSaveDirFile;
                Element groupDiv = box.select(".group-dir ").first();
                if(groupDiv != null){
                    String gDir = groupDiv.text().trim().replace(" ", "");
                    realSaveDirFile = new File(saveDir + "\\" + gDir);
                    realSaveDirFile.mkdirs();
                }else {
                    realSaveDirFile = new File(saveDir);
                }
                File saveFile = new File(realSaveDirFile.getAbsolutePath(), name + ".png");
                System.out.println("download: " + name + " save to：" + saveFile.getAbsolutePath());
                //System.out.println(url);
                Request.Get(url).execute().saveContent(saveFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
