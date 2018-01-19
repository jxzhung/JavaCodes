package com.t600.biz.map360;

import com.google.gson.Gson;
import org.apache.batik.bridge.UserAgent;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2017/7/31.
 */
public class Map360Test {
    String url = "http://ditu.so.com/app/pit?jsoncallback=jQuery18308639111284417877_1501462741835&keyword=%E5%8C%97%E4%BA%AC%20%E6%95%99%E8%82%B2&cityname=%E5%8C%97%E4%BA%AC&city=010&cityid=110000&batch={page}&number=10&citysuggestion=true&qii=true&region_id=&map_cbc=&scheme=https&ext=&regionType=&sid=1000&mobile=1&src=tab_www&region=116.21194018920897%2C39.92726582225359%3B116.72143115600585%2C40.094507318131576&mp=&_=1501465069540";
    String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

    @Test
    public void map() throws IOException, InterruptedException {
        Gson gson = new Gson();
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 1000; i++) {
            String json = getJson(i);
            Map360Resp map360Resp = gson.fromJson(json, Map360Resp.class);
            List<Map360Resp.Item> poi = map360Resp.getPoi();
            for (int i1 = 0; i1 < poi.size(); i1++) {
                Map360Resp.Item item = poi.get(i1);
                String tel = item.getTel();
                if(tel == null || tel.length() < 5){
                    continue;
                }
                String str  = item.getName() + "#" + item.getAddress() + "#" + item.getTel();
                if(list.contains(str)){
                    System.out.println("存在" + str);
                   continue;
                }
                list.add(str);
                System.out.println(str);
            }
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public String getJson(int page) {
        String furl = url.replace("{page}", getBatch(page));
        String text = null;
        try {
            //System.out.println(furl);
            text = Request.Get(furl).addHeader("User-Agent", ua)
                    .addHeader("Referer", "http://ditu.so.com/?k=%20%E5%8C%97%E4%BA%AC%20%E6%95%99%E8%82%B2&src=tab_www")
                    .addHeader("Cookie", "__guid=239254294.3296768140553162000.1501462742253.1008; monitor_count=1; test_cookie_enable=null; __gid=239254294.904594708.1501462742440.1501466852631.43; __sid=239254294.3897069605349599000.1501465004354.9321; erules=p1-19%7Cp4-91%7Cecl-13%7Ckd-1%7Cp2-4%7Cp3-4")
                    .execute().returnContent().asString();
            text = text.substring(text.indexOf("(") + 1, text.lastIndexOf(")"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


    public String getBatch(int page) {
        //batch=2%2C3%2C4%2C5%2C6
        StringBuilder sb = new StringBuilder();
        sb.append(page + 1);
        sb.append("%2C");
        sb.append(page + 2);
        sb.append("%2C");
        sb.append(page + 3);
        sb.append("%2C");
        sb.append(page + 4);
        sb.append("%2C");
        sb.append(page + 5);
        return sb.toString();
    }
}
