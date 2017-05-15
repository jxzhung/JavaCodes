package com.jzhung.demo.spider.zhihu;

import com.jzhung.demo.core.util.DocumentUtil;
import org.apache.http.client.fluent.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 知乎采集器
 * Created by Jzhung on 2017/5/11.
 */
public class ZhiHuMain {
    private BlockingQueue<String> queue = new ArrayBlockingQueue(10000);
    private String imgSaveDir = "E:\\data\\zhihu";

    public static void main(String[] args) {
        ZhiHuMain zhiHuMain = new ZhiHuMain();
        zhiHuMain.start();
    }

    private void start() {
        String qurl = "https://www.zhihu.com/question/39899327";
        parseQuestion(qurl);
        downloadImages();
    }

    private void downloadImages() {
        int tcount = 10;
        ExecutorService pool = Executors.newFixedThreadPool(tcount);
        for (int i = 0; i < tcount; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    String imgUrl = null;
                    try {
                        while ((imgUrl = queue.take())!=null){
                            executeDownload(imgUrl);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private void executeDownload(String imgUrl) {
        String ext = imgUrl.substring(imgUrl.lastIndexOf("."));
        String rdmName = UUID.randomUUID().toString().replace("-", "");
        try {
            File saveFile = new File(imgSaveDir, rdmName + ext);
            Request.Get(imgUrl).execute().saveContent(saveFile);
            System.out.println(Thread.currentThread().getName() + " save: " + imgUrl + " to: " + saveFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseQuestion(String qurl) {
        Document doc = DocumentUtil.getDoc(qurl);
        //System.out.println(doc.html());
        String title = doc.title();

        Element list = doc.getElementsByClass("List").first();
        Elements imgs = list.select("img[src^=https://pic]");
        for (int i = 0; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            String src = img.attr("abs:src");
            if(src.endsWith("_xs.jpg")) //过滤头像
                continue;
            try {
                src = src.replace("_b", ""); //下载大图
                queue.put(src);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    class ZhImage{
        String url;
    }
}
