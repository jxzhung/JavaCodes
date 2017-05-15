package com.jzhung.demo.core.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/2/8.
 */
public class DocumentUtil {
    static String ua = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75";
    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(10000).userAgent(ua).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
