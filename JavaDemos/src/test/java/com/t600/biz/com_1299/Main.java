package com.t600.biz.com_1299;

import com.jzhung.demo.core.util.DocumentUtil;
import org.apache.http.client.fluent.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jzhung on 2017/9/18.
 */
public class Main {
    private BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<Task>(10000);
    private List<Task> taskList = new ArrayList<Task>();
    private static final String DOWN_URL = "http://en1.12999.com/sd.php?f_type1=2&id=";

    class Task {
        String url;
        String name;
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.start();
    }

    private void start() throws InterruptedException {
        getPages();
        //download();
        downloadWithThreads();
    }

    private void downloadWithThreads() {
        ExecutorService poll = Executors.newFixedThreadPool(5);
        int downThread = 5;
        for (int i = 0; i < downThread; i++) {
            poll.execute(new Runnable() {
                public void run() {
                    Task task = null;
                    try {
                        while ((task = taskQueue.take()) != null) {
                            doDownload(task);
                            System.out.println(Thread.currentThread().getName() + " 下载：" + task.name + " 还剩：" + taskQueue.size());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private void doDownload(Task task) {
        String id = task.url.substring(task.url.lastIndexOf("/") + 1, task.url.lastIndexOf("."));
        //System.out.println(id);
        String downloadUrl = DOWN_URL + id;
        try {
            File save = new File("Z:\\初中英语资源\\8年级下册\\5 课件\\未整理", task.name + ".ppt");
            Request.Get(downloadUrl)
                    .addHeader("Referer", "http://en.12999.com/showzipdown.php?id=" + id)
                    .execute().saveContent(save);
                /*Response execute = Request.Get(downloadUrl).execute();
                HttpResponse httpResponse = execute.returnResponse();
                Header[] headers = httpResponse.getHeaders("Content-Disposition");
                for (Header header : headers) {
                    System.out.println(header.getValue());
                }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download() {
        Task task = null;
        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            doDownload(task);
        }

    }

    private void getPages() throws InterruptedException {
        String lurl = "http://en.12999.com/5344.html";
        Document ldoc = DocumentUtil.getDoc(lurl);
        Elements ltables = ldoc.select("table");
        for (int i = 0; i < ltables.size(); i++) {
            Element table = ltables.get(i);
            String text = table.text();
            //找到单元节点
            if (text.startsWith("0")) {
                //System.out.println(i + "===================================");
                //System.out.println(table.text());
                Elements links = table.select("a");
                for (Element link : links) {
                    addToQueue(link.attr("abs:href"));
                }
                break;
            }
        }
    }

    private void addToQueue(String listUrl) throws InterruptedException {
        Document doc = DocumentUtil.getDoc(listUrl);
        //System.out.println(doc);
        Elements tables = doc.select("table");
        for (int i = 0; i < tables.size(); i++) {
            Element table = tables.get(i);
            String text = table.text();
            //找到课件节点
            if (text.startsWith("课件")) {
                //System.out.println(i + "===================================");
                //System.out.println(table.text());
                Elements links = table.select("a");
                for (Element link : links) {
                    Task task = new Task();
                    task.url = link.attr("abs:href");
                    task.name = link.text();
                    taskList.add(task);
                    taskQueue.put(task);
                    System.out.println("添加任务：" + task.name + " URL:" + task.url);
                }
                break;
            }
        }
    }
}
