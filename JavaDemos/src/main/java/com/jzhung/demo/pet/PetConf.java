package com.jzhung.demo.pet;


/** 宠物配置
 * Created by jzhung on 2017/8/18.
 */

public class PetConf {
    private String petName;//宠物名
    private String petCode;//宠物编号
    private String author;//作者

    private String preview;//预览图

    private int width;//宽
    private int height;//高
    private int stepLength;//步长

    private int walkMode;//行走模式 2方向 4方向 8方向


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWalkMode() {
        return walkMode;
    }

    public void setWalkMode(int walkMode) {
        this.walkMode = walkMode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStepLength() {
        return stepLength;
    }

    public void setStepLength(int stepLength) {
        this.stepLength = stepLength;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
