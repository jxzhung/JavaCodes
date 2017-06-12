package com.t600.fuli;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/6/8.
 */
public class FuliUtil {

    public static void main(String[] args) {
        String url = "http://www.fuulea.com/tk/12833/";
        FuliUtil.getFuliQuestion(url);
    }

    static class Question{
        String title;
        String option;
        String answer;
        String video;

        @Override
        public String toString() {
            return "Question{" +
                    "title='" + title + '\'' +
                    ", option='" + option + '\'' +
                    ", answer='" + answer + '\'' +
                    ", video='" + video + '\'' +
                    '}';
        }
    }

    public static Question getFuliQuestion(String url){
        Question question = new Question();
        try {
            Document doc = Jsoup.connect(url).get();

            Element workspace = doc.getElementsByClass("workspace").first();
            Element questionDiv = workspace.child(1);
            if(questionDiv != null){


                // 视频地址
                Element videoDiv = doc.select(".video").first();
                if (videoDiv != null) {
                    Element a = videoDiv.select("a").first();
                    String videoUrl = a.attr("abs:href");
                    Document videoDoc = Jsoup.connect(videoUrl).get();
                    Element source = videoDoc.select("source").first();
                    if(source != null){
                        String videoRealUrl = source.attr("abs:src");
                        question.video = videoRealUrl;
                    }
                }

                //答案
                Element id_answers = doc.select("#id_answers").first();
                if(id_answers != null){
                    String answer = id_answers.text().trim().replace("标准答案: ", "");
                    question.answer = answer;
                }

                //选项
                Element optionDiv = questionDiv.select(".row").first();
                if(optionDiv != null){
                    Elements children = optionDiv.children();
                    boolean delFlag = false;
                    for (Element child : children) {
                        if(child.attr("class").equals("text-muted")){
                            delFlag = true;
                        }
                        if(delFlag)
                            child.remove();
                    }
                }
                question.option = getClearHtml(optionDiv.html());

                optionDiv.remove();
                //题目
                question.title = getClearHtml(questionDiv.html());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(question);
        return question;
    }

    /**
     * 清理HTML仅保留必要标签
     * 规则：
     * img 仅保留src
     * p 去除所有属性
     * span 去除所有属性
     * @param rawHtml 原始HTML
     * @return 清理后的HTML
     */
    public static String getClearHtml(String rawHtml){
        String imageRegex = "<img.*?(src[=]\"[^\"]+\")[^>]+?>";
        rawHtml = rawHtml.replaceAll(imageRegex, "<img $1 />") //仅保留img的src
                .replaceAll(" +", " ")//替换多个空白为一个
                .replaceAll("<p\\s*>", "<p>")//去除p起标签内的空格 <p > -> <p>
                //.replaceAll("<span.+?>", "<span>"); //去除span标签内的属性
                .replaceAll("<span.+?>", "") //去除span标签内的属性
                .replaceAll("</span>", ""); //去除span标签内的属性
        return rawHtml;
    }

}
