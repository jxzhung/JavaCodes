package com.t600.biz;

import com.jzhung.demo.core.util.DocumentUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2017/6/1.
 */
public class E21jy {

    class ExamPoint{
        int id;
        String name;
        String code;
        ExamPoint parent;

        @Override
        public String toString() {
            return "ExamPoint{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", parent='" + (parent!=null?parent.id:"") + '\'' +
                    '}';
        }
    }

    @Test
    public void getExamPoiontTree(){
        String url = "http://tiku.21cnjy.com/tiku.php?mod=quest&channel=2&xd=3";
        Document doc = DocumentUtil.getDoc(url);

        Element con_one_1 = doc.getElementById("con_one_1");
        String rawData = con_one_1.html().replaceAll("<([^>]*)>", "").trim().replace(" ", ".");
        //System.out.println(rawData);
        List<ExamPoint> examPointList = new ArrayList<ExamPoint>();
        ExamPoint lastParent = null;
        int lastLevel = 0;
        int id =1;
        StringTokenizer st = new StringTokenizer(rawData, "\n");
        while (st.hasMoreTokens()){
            String line = st.nextToken().trim();
            if(line.replace(".", "").length() == 0){
                continue;
            }
            char[] chars = line.toCharArray();
            int level = 0;
            for (int i = 0; i < chars.length; i++) {
                if(chars[i]!='.'){
                    level = i/2;
                    break;
                }
            }


            if(level > lastLevel){
                lastParent = examPointList.get(examPointList.size() -1);
            }else if(level == lastLevel){

            }else{
            }
            System.out.println("level:" + level + " parent:" + lastParent);
            ExamPoint examPoint = new ExamPoint();
            examPoint.id = id;
            examPoint.name = line.replace(".", "");
            examPoint.parent = lastParent;



            if(examPoint.parent == null){
                examPoint.code ="_" + examPoint.id;
            }else {
                examPoint.code = lastParent.code + "_" + examPoint.id;
            }





            examPointList.add(examPoint);
            System.out.println(examPoint);
            id++;
        }

       /* for (ExamPoint examPoint : examPointList) {
            System.out.println(examPoint);
        }*/

        /*List<Element> lastLi = new ArrayList<Element>();
        // 所有li包含子级
        Elements li = con_one_1.select("li");
        for (int i = 0; i < li.size(); i++) {
            Element li_1 = li.get(i);
            //System.out.println(li_1.text());
            //最终节点
            if(li_1.select("ul").first() == null){
                //System.out.println(li_1.text());
                lastLi.add(li_1);
            }
        }


        Element lali = lastLi.get(9);
        System.out.println(lali.select("a").first().text());
        System.out.println(lali.parent().parent().select("a").first().text());
        System.out.println(lali.parent().parent().parent().parent().select("a").first().text());
        System.out.println(lali.parent().parent().parent().parent().parent().id());

        System.out.println("----------------");
        System.out.println(lali.select("a").first().text());

        Element parent = lali.parent();
        while (!parent.attr("id").equals("con_one_1")){
            System.out.println(parent.parent().);
            System.out.println("-------------------------------------");
        }*/

        //getLiPath(lali);
        //遍历所有节点
        /*for (int i = 0; i < lastLi.size(); i++) {
            Element lali = lastLi.get(i);
            System.out.print(i);
            System.out.println(lali.text());
            //getLiPath(lali);
        }*/
    }

    private void getLiPath(Element li_1) {
        Element parent = null;
        while (!(parent = li_1.parent()).attr("id").equals("con_one_1")){
            System.out.println(parent.tag());
            //System.out.println(parent.html());
            Element liP = parent.parent();
            if(liP.tag().equals("li")){
                System.out.println(liP.select("a").first().text());
            }
            li_1 = liP;
            System.out.println("-------------------------------------");
        }
    }

   /* public void getChild(Element li){
        Elements ul = li.getElementsByTag("ul").first();
        if(ul != null){
            for (Element element : ul) {

            }
        }
    }*/
}
