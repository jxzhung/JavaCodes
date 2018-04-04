package com.jzhung.goodedu.newbean;

import java.util.List;

/**
 * Created by Jzhung on 2018/3/6.
 */
public class Lesson {
    private String name;
    private String dir;
    private List<Video> videos;

    public Lesson(){}

    public Lesson(String name, List<Video> videos) {
        this.name = name;
        this.videos = videos;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
