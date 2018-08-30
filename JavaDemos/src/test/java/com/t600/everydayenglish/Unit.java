package com.t600.everydayenglish;


import java.util.List;

/**
 * Created by Jzhung on 2018/6/13.
 */
public class Unit {
    String name;
    String url;
    String audioUrl;
    String audio;
    List<Sentence> sentenceList;
    List<Word> importantWordList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public List<Word> getImportantWordList() {
        return importantWordList;
    }

    public void setImportantWordList(List<Word> importantWordList) {
        this.importantWordList = importantWordList;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
