package core.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jzhung on 2016/11/20.
 */
public class RegexTest {
    @Test
    public void test1(){
        Pattern mMenuPtn = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
        String line = "75,生蚝.,生蚝,,,,";
        Matcher matcher = mMenuPtn.matcher(line);
        System.out.println(matcher.group());
    }

    /*   @Test
    public void cssCleaner(){
        String rawHtml = "<p><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">已知函数</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/SY5wHxKvC52b4QjJe4FXf7.png\" alt=\"\" style=\"*//* font-size:13.3333px; *//**//* text-indent:28px; *//**//* white-space:normal; *//*\"><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">且满足</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/SbA7DTtnXnYgJuiwcnu9sf.png\" alt=\"\" style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\"><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">则</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/8sQEfVUQc4ayS4nW6n4XgY.png\" alt=\"\" style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\"><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">的值</span><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">&nbsp;</span><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">（　　）</span></p>";
        *//*Pattern ptn = Pattern.compile(regex);
        Matcher matcher = ptn.matcher(rawHtml);
        while (matcher.find()){}*//*
        List<String> regexList = new ArrayList<String>();
        regexList.add("box-sizing:.+?;");
        regexList.add("background-color:.+?;"); //去除背景
        regexList.add("color:.+?;"); //去除文字颜色
        regexList.add("font-family:.+?; "); //去除字体
        regexList.add("font-size:.+?;"); //去除字体大小
        regexList.add("white-space:.+?;");//去除white-space
        regexList.add("margin.+?;");//去除所有margin
        regexList.add("style=\"\\s*\""); //去除空style

        String clearHtml = rawHtml;

        //遍历去除多余属性
        for (String regex : regexList) {
            clearHtml = clearHtml.replaceAll(regex, "");
            System.out.println(clearHtml);
        }
        clearHtml = clearHtml.replaceAll(" +", " "); //替换多个空白为一个
        clearHtml = clearHtml.replaceAll("<p\\s*>", "<p>"); //<p > -> <p>

        System.out.println("FINAL:");
        System.out.println(clearHtml);
    }*/

    @Test
    public void cssCleaner2(){
        String rawHtml = "<p><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">已知函数</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/SY5wHxKvC52b4QjJe4FXf7.png\" alt=\"\" style=\"/* font-size:13.3333px; *//* text-indent:28px; *//* white-space:normal; */\"><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">且满足</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/SbA7DTtnXnYgJuiwcnu9sf.png\" alt=\"\" style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\"><span style=\"font-size:13.3333px;text-indent:28px;white-space:normal;\">则</span><img src=\"http://s.100tifen.com/media/kindeditor/upload2016/0708/8sQEfVUQc4ayS4nW6n4XgY.png\" alt=\"\" style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\"><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">的值</span><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">&nbsp;</span><span style=\"font-size:13.3333px;line-height:1.5;white-space:normal;text-indent:36.75pt;\">（　　）</span></p>";
        System.out.println(getClearHtml(rawHtml));
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
                .replaceAll("<span.+?>", "<span>"); //去除span标签内的属性
        return rawHtml;
    }
}
