package com.t600.fuli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.t600.fuli.zhangjie.Node;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2017/12/12.
 */
public class PartUtil {
    String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";

    public static void main(String[] args) {
        new PartUtil().start();
    }

    private void start() {
        String courseMapUrl = "http://www.fuulea.com/course/10-map/";
        Node courseNode = getCourseNode(courseMapUrl);
        printNode(courseNode);
    }

    Node getCourseNode(String url){
        Node node = null;
        try {
            String content = Request.Get(url).userAgent(ua)
                    .execute().returnContent().asString();

            Document doc = Jsoup.connect(url).userAgent(ua).get();
            Elements jses = doc.getElementsByTag("script");

            String json = null;
            for (int i = 0; i < jses.size(); i++) {
                Element js = jses.get(i);
                String html = js.html();
                int posStart = -1;
                String sign = "json ={'name':";
                if((posStart = html.indexOf(sign)) != -1){
                    StringTokenizer st = new StringTokenizer(html, "\n");
                    while (st.hasMoreTokens()){
                        String line = st.nextToken();
                        if(line.contains(sign)){
                            json = line.substring(22, line.lastIndexOf("}")+1);
                            json = json.replace("'", "\"");
                            Gson gson3 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
                            node = gson3.fromJson(json, Node.class);
                        }
                    }

                    continue;
                }
                //System.out.println(i + " :" + js.html());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public void printNode(Node node){
        String name = node.getName();
        System.out.println(node.getId() + " " + name + " " + node.getParentId());
        List<Node> children = node.getChildren();
        if(children != null && children.size() > 0){
            for (int i = 0; i < children.size(); i++) {
                Node node1 = children.get(i);
                node1.setParentId(node.getId());
                printNode(node1);
            }
        }
    }
}
