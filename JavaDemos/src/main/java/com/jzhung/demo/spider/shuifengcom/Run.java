package com.jzhung.demo.spider.shuifengcom;

import com.jzhung.demo.core.util.DocumentUtil;
import com.jzhung.demo.spider.no2edu.resdownload.ResFile;
import org.apache.http.client.fluent.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.http.Query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * Created by Jzhung on 2017/2/16.
 */
public class Run {
    private static List<String> listUrls = new ArrayList<String>();
    private static List<String> targetUrls = new ArrayList<String>();
    private static Queue<ResFile> resFileQueue = new ArrayBlockingQueue<ResFile>(5000);
    private static String swfRealUrl = "http://www.shuifeng.net/Dic";
    private static String baseDir = "E:\\swf";
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    private static String refer = "http://www.shuifeng.net/";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        addUrls();
        getTargetUrls();
        downloadResFiles();
    }

    static class ResDownloadRunnable implements Runnable{

        public void run() {
            ResFile resFile;
            while ((resFile = resFileQueue.poll()) != null){
                System.out.println("剩余" + resFileQueue.size());
                if(resFile != null){
                    downRes(resFile.getDestFile(), resFile.getUrl());
                }
            }
        }
    }

    private static void downloadResFiles() {
        new Thread(new ResDownloadRunnable()).start();
        new Thread(new ResDownloadRunnable()).start();
        new Thread(new ResDownloadRunnable()).start();
        new Thread(new ResDownloadRunnable()).start();
        new Thread(new ResDownloadRunnable()).start();
        new Thread(new ResDownloadRunnable()).start();
    }

    private static void addUrls() {
        String baseUrl = "http://www.shuifeng.net/Dic/Html/index";

        listUrls.add("http://www.shuifeng.net/Dic/Html/index.htm");
        for (int i = 2; i < 26; i++) {
            listUrls.add(baseUrl + "00" + i + ".htm");
        }
    }

    public static void getTargetUrls() {
        for (int i = 20; i < listUrls.size(); i++) {
            System.out.println("处理" + (i + 1));
            String url = listUrls.get(i);
            Document doc = DocumentUtil.getDoc(url);
            //System.out.println(doc.html());
            Element table = doc.select("table[bordercolorlight]").first();
            Elements tds = table.select("td");
            for (int j = 0; j < tds.size(); j++) {
                Element td = tds.get(j);
                String text = td.text();
                String link = td.select("a").first().attr("abs:href");
                //System.out.println(text);
                //System.out.println(link);
                ResFile resFile = new ResFile();
                resFile.setUrl(link);
                resFile.setDestFile(text);
                resFileQueue.add(resFile);
                //downRes(text, link);
            }
        }
    }

    private static void downRes(String name, String link) {
        Document doc = DocumentUtil.getDoc(link);
        //System.out.println(doc.html());
        Element script = doc.select("script").get(1);
        //System.out.println(script.html());
        String scriptStr = script.html();
        String swf = scriptStr.substring(scriptStr.indexOf("/"), scriptStr.indexOf("\","));
        String realUrl = swfRealUrl + swf;
        File file = new File(baseDir, name + ".swf");
        if(file.exists()){
            file.delete();
        }
        try {
            Request.Get(realUrl).addHeader("Referer", refer).addHeader("User-Agent", userAgent).execute().saveContent(file);
            System.out.println("下载" + realUrl + " 保存：" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
