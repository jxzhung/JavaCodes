package com.jzhung.demo.spider;

/**
 * Created by Jzhung on 2017/5/10.
 */
public class TikuExamMain {
    private String url;

    public static void main(String[] args) {
        TikuExamMain tk = new TikuExamMain();
        String url =  "http://www.tiku.com/testPaper.html?hdSearch=&key=&sct=0&cn=%E6%95%B0%E5%AD%A6&st=2&cid=500004&bid=800009&vid=800005&sort=0";
        tk.start(url);
    }

    /**
     * 教材URL
     * @param url
     */
    private void start(String url) {

    }
}
