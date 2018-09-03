package com.jzhung.demo.pet;

import com.google.gson.Gson;

/**
 * Created by Jzhung on 2017/8/21.
 */
public class PetMain {
    public static void main(String[] args) {
        Gson gson = new Gson();
        PetConf petConf = new PetConf();
        petConf.setPetName("杂鱼");
        petConf.setWidth(50);
        petConf.setHeight(50);
        petConf.setAuthor("网络");
        petConf.setPetCode("M001");
        petConf.setPreview("preview.png");
        petConf.setStepLength(5);
        petConf.setWalkMode(4);
        String json = gson.toJson(petConf);
        System.out.println(json);
    }
}
