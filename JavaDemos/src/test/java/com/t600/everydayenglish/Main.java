package com.t600.everydayenglish;

import com.google.gson.Gson;
import com.jzhung.demo.core.util.DocumentUtil;
import com.jzhung.demo.core.util.FileUtil;
import org.apache.http.client.fluent.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2018/6/13.
 */
public class Main {
    private List<String> bookUrlList = new ArrayList<String>();
    private List<Book> bookList = new ArrayList<Book>();
    private Gson gson = new Gson();
    private String baseDir;

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        baseDir = "F:\\Data\\EverydayEnglish";
        //testUnit();


        addBookUrlList();

        // 抓取书本和单元
        for (String bookUrl : bookUrlList) {
            processBook(bookUrl);
        }

        // 抓取单元
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            List<Unit> unitList = book.unitList;
            if (unitList != null) {
                for (int j = 0; j < unitList.size(); j++) {
                    Unit unit = unitList.get(j);
                    processUnit(unit);
                }
            }
        }

        String json = gson.toJson(bookList);
        File jsonFile = new File(baseDir, "books.json");
        FileUtil.saveToFile(jsonFile.getAbsolutePath(), json);
        System.out.println(json);
    }

    private void testUnit() {
        String url = "https://dict.eudic.net/webting/desktopplay?id=007cc27c-5ba7-4f9c-919b-8e85ac4e9a79&token=QYN+eyJ0b2tlbiI6IiIsInVzZXJpZCI6IiIsInVybHNpZ24iOiJrOHRDa1lhWWwzSGRTQmRDSnFTNEVONTJOSGs9IiwidCI6IkFCSU1UVTBPRFk1TURVek5BPT0ifQ%3D%3D";
        Unit unit = new Unit();
        unit.url = url;
        processUnit(unit);
        System.out.println(gson.toJson(unit));
    }

    /**
     * 抓取一个单元
     *
     * @param unit
     */
    private void processUnit(Unit unit) {
        Document doc = DocumentUtil.getDoc(unit.url);
        System.out.println("抓取单元：" + unit.url);

        // 音频
        String html = doc.html();
        String symbolStart = "initPlayPage(\"";
        String audioUrl = html.substring(html.indexOf(symbolStart) + symbolStart.length(), html.indexOf(".mp3") + 4);
        System.out.println("抓取音频：" + audioUrl);
        String fileName = audioUrl.substring(audioUrl.lastIndexOf("/") + 1, audioUrl.length());
        File saveFile = new File(baseDir, fileName);
        if(!saveFile.exists()){
            try {
                Request.Get(audioUrl).execute().saveContent(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        unit.audio = fileName;
        unit.audioUrl = audioUrl;

        Element articleDiv = doc.select("#article").first();
        Elements pList = articleDiv.select("p");


        List<String> englishList = new ArrayList<String>();
        for (int i = 0; i < pList.size(); i++) {
            Element englishP = pList.get(i);
            englishList.add(englishP.text());
        }

        // 中文
        String cnStrSymbolStart = "var translate = ";
        String cnStrSymbolEnd = "if(translate['has_translation";
        String chineseJson = html.substring(html.indexOf(cnStrSymbolStart) + cnStrSymbolStart.length(), html.indexOf(cnStrSymbolEnd) - 5);
        // System.out.println(chineseJson);
        EeChineseJson eeChineseJson = gson.fromJson(chineseJson, EeChineseJson.class);
        List<EeChineseJson.TranslationBean> translation = eeChineseJson.getTranslation();

        // 中文条数
        List<Sentence> sentenceList = new ArrayList<Sentence>();
        unit.sentenceList = sentenceList;


        for (int i = 0; i < translation.size(); i++) {
            EeChineseJson.TranslationBean translationBean = translation.get(i);
            int order = translationBean.getOrder();

            String chinese = translationBean.getText();
            String timestamps = translationBean.getTimestamps();
            String startTime = timestamps.substring(timestamps.indexOf("[") + 1, timestamps.indexOf("],["));
            String endTime = timestamps.substring(timestamps.indexOf("],[") + 3, timestamps.length() - 1);
            Sentence sentence = new Sentence();
            sentence.english = englishList.get(sentenceList.size());
            sentence.chinese = chinese;
            sentence.startTime = startTime;
            sentence.endTime = endTime;
            sentenceList.add(sentence);

            // 后项遍历 相同order的追加到前面的句子中
            for (int j = i + 1; j < translation.size(); j++) {
                EeChineseJson.TranslationBean next = translation.get(j);
                if (next.getOrder() == order) {
                    sentence.chinese = sentence.chinese + next.getText();
                    String t2 = translationBean.getTimestamps();
                    String endTime2 = t2.substring(t2.indexOf("],[") + 3, t2.length() - 1);
                    sentence.endTime = endTime2;
                } else {
                    i = j - 1;
                    break;
                }
            }

            if (sentenceList.size() == englishList.size()) {
                break;
            }
        }

        // 重点单词
        List<Word> wordList = new ArrayList<Word>();
        unit.setImportantWordList(wordList);
        List<EeChineseJson.WordhintsBean> wordhints = eeChineseJson.getWordhints();
        if (wordhints != null) {
            for (int i = 0; i < wordhints.size(); i++) {
                EeChineseJson.WordhintsBean wordhintsBean = wordhints.get(i);
                List<EeChineseJson.WordhintsBean.ItemsBean> items = wordhintsBean.getItems();
                if (items != null) {
                    for (int j = 0; j < items.size(); j++) {
                        EeChineseJson.WordhintsBean.ItemsBean itemsBean = items.get(j);
                        String word = itemsBean.getWord();
                        String meaning = itemsBean.getExp();
                        Word w = new Word();
                        w.setWord(word);
                        w.setMeaning(meaning);
                        w.setDifficulty(wordhintsBean.getDifficulty());
                        wordList.add(w);
                    }
                }
            }
        }
    }

    /**
     * 抓取一本书
     *
     * @param bookUrl
     */
    private void processBook(String bookUrl) {
        Document doc = DocumentUtil.getDoc(bookUrl);

        String title = doc.title();
        title = title.substring(0, title.indexOf("|"));
        Book book = new Book();
        book.name = title;
        book.url = bookUrl;
        bookList.add(book);

        List<Unit> unitList = new ArrayList<Unit>();
        book.unitList = unitList;

        System.out.println("抓取课本：" + title + " " + bookUrl);
        Element conDiv = doc.select(".contents").first();
        Elements unitDivs = conDiv.select("a");
        for (int i = 0; i < unitDivs.size(); i++) {
            Element link = unitDivs.get(i);
            String unitHref = link.attr("abs:href");
            if (unitHref == null || unitHref.equals("")) {
                continue;
            }
            String unitTitle = link.attr("title");
            System.out.println(unitTitle + " URL:" + unitHref);

            Unit unit = new Unit();
            unit.name = unitTitle;
            unit.url = unitHref;
            unitList.add(unit);
        }
    }

    private void addBookUrlList() {
        // 外研7 8 9
        bookUrlList.add("https://dict.eudic.net/ting/article?id=a1ef6f97-309b-11e7-aa21-000c29ffef9b");
        bookUrlList.add("https://dict.eudic.net/ting/article?id=bc24f4e5-309b-11e7-aa21-000c29ffef9b");
        bookUrlList.add("https://dict.eudic.net/ting/article?id=cde4855c-309b-11e7-aa21-000c29ffef9b");

        // 新概念 美音 四册
        bookUrlList.add("https://dict.eudic.net/ting/article?id=aa3cfe56-6f8b-498e-9d0c-78cd68c0a35e");
        bookUrlList.add("https://dict.eudic.net/ting/article?id=dc41fd26-34c7-4546-abf3-0d2f83af5095");
        bookUrlList.add("https://dict.eudic.net/ting/article?id=93f70059-79e6-43b8-a37f-62302c2dc36f");
        bookUrlList.add("https://dict.eudic.net/ting/article?id=78a5501b-66d6-42f4-b22a-7d6ae377be2d");
    }

}
