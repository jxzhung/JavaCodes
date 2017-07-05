package com.t600.biz;

import com.google.gson.Gson;
import com.t600.biz.baidu.BaiduRespContent;
import com.t600.biz.baidu.BaiduRespResult;
import com.t600.biz.entity.KeySearchResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.util.List;

/**
 * 获取北京艺考培训机构信息
 * Created by Jzhung on 2017/6/15.
 */
public class PeiXunBan {
    private static final String ADDRESS_URL = "http://ditu.amap.com/service/poiInfo?query_type=TQUERY&pagesize=100&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=10&city=110000&geoobj=115.522281%7C39.455639%7C117.499821%7C40.402346&keywords=";
    private static final String BAIDU_ADDRESS_URL = "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=alamap&pcevaname=pc4.1&qt=con&from=webmap&c=131&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&&tn=B_NORMAL_MAP&u_loc=12947997,4845792&ie=utf-8&l=11&b=(12864901.865,4806566.585;13059717.865,4867494.585)&t=1497494591778";
    private static Gson gson = new Gson();

    @Test
    public void getInfos() throws Exception {
        //getContraceInfo("北京 高考培训");
        String saveFile = "E:\\北京艺术学校-有电话.txt";
        getInfoFromBaiduMap(saveFile, "北京艺术学校");
    }

    /**
     * 从百度地图获取地址和电话
     *
     * @param name
     * @return
     */
    public static void getInfoFromBaiduMap(String saveFile, String name) throws Exception {

        File file = new File(saveFile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));


        String encodeName = URLEncoder.encode(name, "utf-8");
        int pn = 0;
        int nn = 0;
        String url = String.format(BAIDU_ADDRESS_URL, encodeName, pn, nn);
        System.out.println(url);
        String text = Request.Get(url)
                .addHeader("Cookie", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                .addHeader("Referer", "http://ditu.amap.com/search?")
                .execute().returnContent().asString();
        System.out.println(text);

        String result = "{" + text.substring(text.indexOf("\"result\""), text.indexOf("\"damoce\"") - 1) + "}";
        System.out.println(result);
        String contentStr = "{" + text.substring(text.indexOf("\"content\""), text.indexOf("\"current_city\"") - 1) + "}";
        System.out.println(contentStr);


        BaiduRespResult resultBean = gson.fromJson(result, BaiduRespResult.class);

        BaiduRespContent contentBean;


        int total = resultBean.getResult().getTotal();
        System.out.println("总记录：" + total);

        int page = total / 10 + 1;
        System.out.println("总页数" + page);
        for (int i = 0; i < page; i++) {
            System.out.println("当前页" + i);
            pn = i;
            nn = 10 * i;
            url = String.format(BAIDU_ADDRESS_URL, encodeName, pn, nn);
            //System.out.println(url);
            text = Request.Get(url).execute().returnContent().asString();

            int contentIndex = text.indexOf("\"content\"");
            int contentCityIndex = text.indexOf("\"current_city\"");
            if (contentIndex < 0 || contentCityIndex < 0) {
                continue;
            }

            text = "{" + text.substring(contentIndex, text.indexOf("\"current_city\"") - 1) + "}";
            //System.out.println(text);

            contentBean = gson.fromJson(text, BaiduRespContent.class);
            List<BaiduRespContent.ContentBean> content = contentBean.getContent();

            if (content != null && content.size() > 0) {
                for (int m = 0; m < content.size(); m++) {
                    BaiduRespContent.ContentBean curContent = content.get(m);
                    String itemName = curContent.getName();
                    String address = curContent.getAddr();
                    String tel = curContent.getTel();
                    if (tel == null) {
                        tel = "";
                        continue;
                    }
                    System.out.println(itemName + "#" + address + "#" + tel);
                    bw.write(itemName + "#" + address + "#" + tel);
                    bw.newLine();
                }
            } else {
                System.out.println("无列表");
            }
            bw.flush();
        }
        bw.close();
    }

    /**
     * 从高德地图获取地址和电话
     *
     * @param name
     * @return
     */
    public static void getContraceInfo(String name) throws Exception {
        String url = ADDRESS_URL + URLEncoder.encode(name, "utf-8");

        HttpResponse httpResponse = Request.Get(url).execute().returnResponse();
        org.apache.http.Header[] headers = httpResponse.getHeaders("Content-Type");
        String text = Request.Get(url).execute().returnContent().asString();
        System.out.println(text);

        KeySearchResponse resp = gson.fromJson(text, KeySearchResponse.class);
        List<KeySearchResponse.DataBean.PoiListBean> poi_list = resp.getData().getPoi_list();
        if (poi_list != null && poi_list.size() > 0) {
            for (int i = 0; i < poi_list.size(); i++) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(i);
                String itemName = poiListBean.getName();
                String address = poiListBean.getAddress();
                String tel = poiListBean.getTel();

                System.out.println("" + itemName + " 地址：" + address + " 电话：" + tel);
            }
        } else {
            System.out.println("无列表");
        }
    }
}
