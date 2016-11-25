package com.jzhung.demo.spider.kejian1;

import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2016/11/25.
 */
public class Main {
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    private List<String> urlList = new ArrayList<String>();
    private List<String> softIdList = new ArrayList<String>();

    public static void main(String[] args) {
        Main main = new Main();
        //main.getList();

        main.softIdList.add("169906");
        main.getDownloadUrl();
    }

    private void getList() {
        String baseUrl = "http://www.1kejian.com";
        String url = "http://www.1kejian.com/edu/2/34/kejian_34_3.html";
        try {
            //String content = Request.Get(url).userAgent(UA).execute().returnContent().asString(Charset.forName("gb2312"));
            Document doc = Jsoup.parse(new File("E:\\t.html"), "gb2312");
            Elements ems = doc.getElementById("list_m").select("a[href]");
            for (Element e : ems) {
                String href = baseUrl + e.attr("href");
                if (href.contains("/edu")) {
                    System.out.println(href);
                    urlList.add("href");
                    String softId = href.substring(href.lastIndexOf("/") + 9, href.lastIndexOf("."));
                    System.out.println(softId);
                    softIdList.add(softId);
                }
            }
            //System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDownloadUrl() {
        try {
            for (String softId : softIdList) {
                String url = "http://www.1kejian.com/edu/softdown.asp?softid=" + softId;
                String content = Request.Get(url).userAgent(UA).execute().returnContent().asString(Charset.forName("gb2312"));
                Element forma = Jsoup.parse(content).getElementById("rnd").parent().child(5);
                String down_html = forma.outerHtml();
                String id = d
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
