package com.jzhung.goodedu;

import com.google.gson.Gson;
import com.jzhung.goodedu.bean.Item;
import com.jzhung.goodedu.bean.ListConfig;
import com.jzhung.goodedu.newbean.Lesson;
import com.jzhung.goodedu.newbean.LessonData;
import com.jzhung.goodedu.newbean.Video;
import com.qiniu.util.Base64;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2018/3/5.
 */
public class LessonConfigGen {
    public static void main(String[] args) {
        //genLessonConfig();
        //genVideoListConfig();
        genVideoData();
    }

    private static void genVideoData() {
        LessonData ld = new LessonData();
        List<Lesson> lessons = new ArrayList<Lesson>();
        ld.setLessons(lessons);
        String[] lessonArr = {"语文", "数学", "英语", "政治", "历史", "地理", "物理", "化学", "生物"};

        int videoCount = 20;

        for (String lessonName : lessonArr) {
            List<Video> videos = new ArrayList<Video>();
            for (int i = 0; i < videoCount; i++) {
                Video video = new Video();
                video.setName(lessonName + "视频" + (i + 1));
                video.setInfo(lessonName);
                video.setLength("");
                //video.setPath("video" + (i + 1) + ".mp4");
                //video.setPath("/" + lessonName + "/" + (i + 1) + ".mp4");
                video.setPath("/1.mp4");
                video.setSize(0);
                videos.add(video);
            }
            Lesson lesson = new Lesson(lessonName, videos);
            lesson.setDir("/" + lessonName);
            lessons.add(lesson);
        }
        Gson gson = new Gson();
        String json = gson.toJson(ld);
        System.out.println(json);
    }

    private static void genLessonConfig() {
        ListConfig lessonConfig = new ListConfig();
        lessonConfig.setVersion("1");
        List<Item> lessons = new ArrayList<Item>();
        String lesson = "语文";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "数学";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "英语";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));

        lesson = "政治";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "历史";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "地理";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));

        lesson = "物理";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "化学";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lesson = "生物";
        lessons.add(new Item(lesson, Base64.encodeToString(lesson.getBytes(), Base64.DEFAULT)));
        lessonConfig.setItemList(lessons);

        String json = new Gson().toJson(lessonConfig).replace("\\n", "");
        System.out.println(json);

    }

    private static void genVideoListConfig() {
        ListConfig conf = new ListConfig();
        conf.setVersion("1");
        List<Item> items = new ArrayList<Item>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            String name = "课程" + (i + 1);
            items.add(new Item(name, Base64.encodeToString((name + ".mp4").getBytes(), Base64.DEFAULT)));
        }
        conf.setItemList(items);

        String json = new Gson().toJson(conf).replace("\\u003d", "").replace("\\n", "");
        System.out.println(json);
    }
}
