package com.jzhung.demo.spider.xpath;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jzhung on 2017/4/28.
 */
public class JsoupXpathTest {

    @Test
    public void xpathTest1() throws IOException, XpathSyntaxErrorException {
        String url = "";
        Document doc = Jsoup.connect("http://s.dearedu.com/list.php?g=1&su=1&e=76&ed=7&un=179&ut=46").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").get();
        JXDocument  doubanTest = new JXDocument(doc);
        List<Object> sel = doubanTest.sel("/html/body/div[1]/div[2]/div[3]/div[2]/div/div[2]/div[1]/h1/a");
        for (Object o : sel) {
            System.out.println(o);
        }
    }
}
