package com.jzhung.demo.spider.cnjy21;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/1/11.
 */
public class Run {
    public static void main(String[] args) {

    }


    @Test
    public void getQuestions() {
        String url = "http://tiku.21cnjy.com/tiku.php?mod=quest&channel=7&xd=2&catid=9788&page=2";
        try {
            String content = Request.Get(url).execute().returnContent().asString();
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
