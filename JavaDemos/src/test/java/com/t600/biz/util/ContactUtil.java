package com.t600.biz.util;

import com.google.gson.Gson;
import com.t600.biz.entity.ContractInfo;
import com.t600.biz.entity.KeySearchResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/** 联系方式工具类
 * Created by Jzhung on 2017/5/23.
 */
public class ContactUtil {
    private static final String POST_CODE_URL = "http://opendata.baidu.com/post/s?wd=";
    private static final String ADDRESS_URL = "http://ditu.amap.com/service/poiInfo?query_type=TQUERY&pagesize=20&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=14&city=440100&geoobj=113.289334%7C23.121279%7C113.412931%7C23.150956&keywords=";
    private static Gson gson = new Gson();
    private static boolean chooseContraceInfo = false; //是否手动选择教育局联系信息


    /**
     * 从学校地址中获取县、市、区
     * @param address
     * @return
     */
    public static String getCityFromAddress(String address){
        int index;
        if(address.contains("自治区")){
            String tmp = address.replace("自治区", "替替替");
            index = tmp.indexOf("区");
            if(index == -1){
                index = tmp.indexOf("县");
            }
            if(index == -1){
                index = tmp.indexOf("市");
            }
        }else {
            index = address.indexOf("区");
            if(index == -1){
                index = address.indexOf("县");
            }
            if(index == -1){
                index = address.indexOf("市");
            }
        }
        if(index == -1){
            return address;
        }
        return address.substring(0, index +1);
    }

    /**
     * 获取邮编
     *
     * @param name
     * @return
     */
    public static String getPostCode(String name) {
        String postCode = "";

        String url = POST_CODE_URL + name;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(BizConst.ua).timeout(5000).get();
            Element list = doc.getElementsByTag("ul").first();
            if (list != null) {
                Element a = list.select("a[href]").first();
                if (a != null) {
                    String html = a.html();
                    int index = html.lastIndexOf(">");
                    if (index + 2 < html.length()){
                        postCode = html.substring(index + 2).trim();
                    }
                    //System.out.println(postCode);
                } else {
                    System.out.println("无A");
                }
            } else {
                System.out.println("无list");
            }
            //System.out.println(doc.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postCode;
    }

    /**
     * 获取教育局地址和电话
     *
     * @param name
     * @return
     */
    public static ContractInfo getContraceInfo(String name) throws Exception {

        ContractInfo contractInfo = new ContractInfo();
        String url = ADDRESS_URL + URLEncoder.encode(name, "utf-8");

        HttpResponse httpResponse = Request.Get(url).execute().returnResponse();
        org.apache.http.Header[] headers = httpResponse.getHeaders("Content-Type");

            /*for (Header header : headers) {
            }*/

        String text = Request.Get(url).execute().returnContent().asString();
        KeySearchResponse resp = gson.fromJson(text, KeySearchResponse.class);
        List<KeySearchResponse.DataBean.PoiListBean> poi_list = resp.getData().getPoi_list();
        if (poi_list != null && poi_list.size() > 0) {

            //自动选择的情况
            if (!chooseContraceInfo) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(0);
                String itemName = poiListBean.getName();
                if (itemName.contains("教育局")) {
                    String address = poiListBean.getAddress();
                    String tel = poiListBean.getTel();
                    System.out.println("自动选择了 " + itemName + " 地址：" + address + " 电话：" + tel);
                    contractInfo.address = address;
                    contractInfo.tel = tel;
                }
                TimeUnit.SECONDS.sleep(1); //防止返回错误数据
                return contractInfo;
            }

            System.out.println("====> 请选择 " + name + " 的联系信息");
            int total = poi_list.size();
            if (total > 5) {
                total = 5;
            }
            for (int i = 0; i < total; i++) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(i);
                String name1 = poiListBean.getName();
                String address = poiListBean.getAddress();
                String tel = poiListBean.getTel();
                System.out.println((i + 1) + ". " + name1 + " 地址：" + address + " 电话：" + tel);
            }
            System.out.println("输入序号: ");

            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            x = x - 1;

            if (x >= 0 && x < total) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(x);
                String name1 = poiListBean.getName();
                String address = poiListBean.getAddress();
                String tel = poiListBean.getTel();
                System.out.println("你选择了 " + name1 + " 地址：" + address + " 电话：" + tel);
                contractInfo.address = address;
                contractInfo.tel = tel;
            } else {
                System.out.println("输入错误");
            }

        } else {
            System.out.println("无建议列表");
        }

//            System.out.println(text);


        return contractInfo;
    }
}
