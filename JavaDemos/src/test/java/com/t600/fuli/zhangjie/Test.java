package com.t600.fuli.zhangjie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@SuppressWarnings("serial")
public class Test {

    private static List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    static{

        list.add(new HashMap<String,String>(){{put("KEY1","A");put("KEY2","1");put("KEY3","I");}});
        list.add(new HashMap<String,String>(){{put("KEY1","A");put("KEY2","1");put("KEY3","J");}});
        list.add(new HashMap<String,String>(){{put("KEY1","A");put("KEY2","2");put("KEY3","K");}});
        list.add(new HashMap<String,String>(){{put("KEY1","A");put("KEY2","2");put("KEY3","L");}});

        list.add(new HashMap<String,String>(){{put("KEY1","B");put("KEY2","3");put("KEY3","M");}});
        list.add(new HashMap<String,String>(){{put("KEY1","B");put("KEY2","3");put("KEY3","N");}});
        list.add(new HashMap<String,String>(){{put("KEY1","B");put("KEY2","4");put("KEY3","O");}});
        list.add(new HashMap<String,String>(){{put("KEY1","B");put("KEY2","4");put("KEY3","P");}});

    }


    public static void main(String[] args) {

        Node node = new Node("ROOT");

        for(Map<String,String> map : list){
            node.insert(map.get("KEY1")).insert(map.get("KEY2")).insert(map.get("KEY3"));
        }

        Gson gson3 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String prettyJsonStr = gson3.toJson(node.getChildren());

        System.out.println(prettyJsonStr);
    }
}