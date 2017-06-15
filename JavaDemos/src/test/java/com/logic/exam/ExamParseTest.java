package com.logic.exam;

import org.apache.xerces.dom3.as.ASDataType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jzhung on 2017/6/14.
 */

public class ExamParseTest {
    private Pattern titlePtn = Pattern.compile("^\\d+．.*$");
    private Pattern optionPtn = Pattern.compile("^[ABCD]．.*$");

    private static final int TITLE_LINE = 1;
    private static final int PAGER_NAME_LINE = 2;
    private static final int OPTION_LINE = 3;
    private static final int ANSWER_LINE = 4;
    private static final int EXPLAIN_LINE = 5;
    private static final int UNKNOW = 6;

    class Context{
        Paper paper;
        int scene; //场景
        boolean titleOk;
        boolean containsOption;
        boolean optionOk;
        boolean answerOk;
        boolean explainOk;
    }


    class Paper{
        String name;
        List<Question> questionList;
    }

    class Question{
        String label;
        String title;
        String option;
        String answer;
        String explain;
    }

    @Test
    public void testTitlePtn(){
        String text = "1．图中最有利于雾霾大气污染物扩散的情形是(　　)";
        System.out.println(text);
        Matcher matcher = Pattern.compile("^\\d+．.*$").matcher(text);
        if(matcher.matches()){
            System.out.println("匹配");
        }else{
            System.out.println("不匹配");
        }
    }

    @Test
    public void parse() throws IOException {
        String file = "C:\\Users\\Jzhung\\Desktop\\1.html";
        Document doc = Jsoup.parse(new File(file), "gbk");
        Elements ps = doc.select("p");
        Context context = new Context();
        for (int i = 0; i < ps.size(); i++) {
            Element p = ps.get(i);
            getScene(p, context);
        }
    }

    public void getScene(Element e, Context context){
        String text = e.text();

        String clearText = text.trim().replace(" ", "");
        if(!context.titleOk){
            Matcher matcher = titlePtn.matcher(clearText);
            if(matcher.matches()){
                System.out.println("=====================标题：" + text);
                //context.titleOk = true;
            }
        }

        if(!context.optionOk){
            Matcher matcher = optionPtn.matcher(clearText);
            if(matcher.matches()){
                System.out.println("====选项" + text);
            }
            if(clearText.startsWith("D．")){
//                context.optionOk = true;
            }
        }
    }
}
