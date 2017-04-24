package com.jzhung.util.doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文档工厂
 * Created by Jzhung on 2017/3/23.
 */
public class DocFactory {
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    public static String dir = "E:\\data\\HtmlCache";

    public static void main(String[] args) {
        String url = "http://tiku.21cnjy.com/tiku.php?mod=quest&channel=7&xd=3&catid=21512&page=10";
        Document doc = get(url);
        Elements a = doc.select("a[href]");
        System.out.println("超链接数量:" + a.size());
        for (Element element : a) {
            String href = element.attr("href");
            System.out.println(element.html());
            System.out.println("GET " + href);
            if (href.length() < 5) continue;
            get(href);
        }
    }

    public static Document get(String url) {
        String rawUrl = url.toLowerCase().replace("http://", "").replace("https://", "").replace("?", "").replace("/", "");
        File htmlFile = new File(dir, rawUrl + ".txt");
        Document document = null;
        try {
            if (htmlFile.exists()) {
                System.out.println("存在：" + htmlFile.getName());
                document = Jsoup.parse(htmlFile, "utf-8");
            } else {
                document = Jsoup.connect(url).userAgent(userAgent).get();
                save(document, htmlFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static void save(Document document, File htmlFile) {
        try {
            FileOutputStream fout = new FileOutputStream(htmlFile);
            fout.write(document.html().getBytes());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
