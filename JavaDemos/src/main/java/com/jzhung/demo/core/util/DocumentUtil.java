package com.jzhung.demo.core.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/2/8.
 */
public class DocumentUtil {
    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(10000).userAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
