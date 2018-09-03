package com.t600.ciku;

import com.jzhung.demo.core.util.FileUtil;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2017/12/22.
 */
public class WordUtil {
    private List<String> list = new ArrayList<String>();
    String baseUrl = "http://www.youdao.com/w/%s";

    @Test
    public void getWordInfo() throws IOException {


        String word = "hello";

        String fileContent = FileUtil.getFileContent("C:\\Users\\Jzhung\\Desktop\\word.txt");
        StringTokenizer st = new StringTokenizer(fileContent, "\n");
        int index = 0;
        while (st.hasMoreTokens()) {
            index++;

            word = st.nextToken();

            //System.out.println(index + " " + word);

            String rightWord = getRightWord(word);
            //System.out.println(index + " " + word + " -> " + rightWord);
            System.out.println(rightWord);

        }
        System.out.println("======================错误列表");
        for (String s : list) {
            System.out.println(s);
        }
    }

    public String getRightWord(String word) {
        String right = null;
        String realUrl = String.format(baseUrl, word);
        Document doc = null;
        try {
            doc = Jsoup.connect(realUrl).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").get();
            Element error = doc.select(".error-wrapper").first();
            if (error != null) {
                if (word.endsWith("d") || word.endsWith("v") || word.endsWith("n") || word.endsWith("a")) {
                    right = word.substring(0, word.length() - 1);
                    String str = word + " -> " + right;
                    list.add(str);
                } else {
                    list.add("error: " + word);
                    //System.out.println("error: " + word);
                    return word + " 错误";
                }

            } else {
                //System.out.println(index);
                right = word;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return right;
    }

}
