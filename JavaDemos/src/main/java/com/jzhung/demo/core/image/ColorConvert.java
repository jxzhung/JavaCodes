package com.jzhung.demo.core.image;

import java.awt.*;

/** 原始颜色和要替换的颜色
 * Created by Jzhung on 2017/4/19.
 */
public class ColorConvert {
    private int srcColor;
    private int dstColor;

    public ColorConvert(int srcColor, int dstColor) {
        this.srcColor = srcColor;
        this.dstColor = dstColor;
    }

    public ColorConvert(String srcColor, String dstColor) {
        int src = Color.decode(srcColor).getRGB();
        int dst = Color.decode(dstColor).getRGB();
        this.srcColor = src;
        this.dstColor = dst;
    }

    public int getSrcColor() {
        return srcColor;
    }

    public void setSrcColor(int srcColor) {
        this.srcColor = srcColor;
    }

    public int getDstColor() {
        return dstColor;
    }

    public void setDstColor(int dstColor) {
        this.dstColor = dstColor;
    }
}
