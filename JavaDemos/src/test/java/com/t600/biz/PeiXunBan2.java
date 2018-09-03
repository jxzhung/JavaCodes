package com.t600.biz;

import com.google.gson.Gson;
import com.t600.biz.baidu.BaiDuRespContent2;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取北京艺考培训机构信息 直接贴结果
 * Created by Jzhung on 2017/6/15.
 */
public class PeiXunBan2 {
    private static final String ADDRESS_URL = "http://ditu.amap.com/service/poiInfo?query_type=TQUERY&pagesize=100&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=10&city=110000&geoobj=115.522281%7C39.455639%7C117.499821%7C40.402346&keywords=";
    private static final String BAIDU_ADDRESS_URL = "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=alamap&pcevaname=pc4.1&qt=con&from=webmap&c=131&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&&tn=B_NORMAL_MAP&u_loc=12947997,4845792&ie=utf-8&l=11&b=(12864901.865,4806566.585;13059717.865,4867494.585)&t=1497494591778";
    String ak = "mk1RBykGGBEbQdynmRB2jbrWt7VLHSG6";
    private static Gson gson = new Gson();

    private static Map<String, String> cityMap = new HashMap<String, String>();

    static {
        cityMap.put("北京", BAIDU_ADDRESS_URL);
        cityMap.put("天津", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=332&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=13&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(13024666.9,4702414.51;13069946.9,4719438.51)&t=1500259805854");
        cityMap.put("石家庄", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=150&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12703546.9,4538158.51;12794106.9,4572206.51)&t=1500261023583");
        cityMap.put("太原", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=176&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=13&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12507722.9,4521454.51;12553002.9,4538478.51)&t=1500261136119");
        cityMap.put("郑州", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=268&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12606378.9,4099502.51;12696938.9,4133550.51)&t=1500261220576");
        cityMap.put("西安", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=233&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=11&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12053834.780000001,4009482.1550000003;12234954.780000001,4077578.1550000003)&t=1500261482208");
        cityMap.put("济南", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=con&from=webmap&c=288&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12974250.780000001,4338570.155;13064810.780000001,4372618.155)&t=1500261589143");
        cityMap.put("南京", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=315&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(13196286.43,3734641.3449999997;13262974.43,3764209.3449999997)&t=1500433699962");
        cityMap.put("广州", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=257&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=10&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12419054.43,2583449.3449999997;12808686.43,2675353.3449999997)&t=1500451959316");
        cityMap.put("长沙", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=158&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=13&tn=B_NORMAL_MAP&u_loc=12948021,4845795&ie=utf-8&b=(12555957,3250771;12604661,3265139)&t=1500613582531");
        cityMap.put("武汉", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=218&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=13&tn=B_NORMAL_MAP&u_loc=12948031,4845787&ie=utf-8&b=(12703455,3551643;12752159,3566299)&t=1500967817262");
        cityMap.put("合肥", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=127&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&tn=B_NORMAL_MAP&u_loc=12948031,4845787&ie=utf-8&b=(13004575,3704379;13101983,3733691)&t=1500968058326");
        cityMap.put("呼和浩特", "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=con&from=webmap&c=321&wd=%s&wd2=&pn=%d&nn=%d&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=11&tn=B_NORMAL_MAP&u_loc=12948031,4845787&ie=utf-8&b=(12351135,4930683;12545951,4989307)&t=1500968178479");
    }

    @Test
    public void getInfos() throws Exception {
        //getContraceInfo("北京 高考培训");
        String city = "呼和浩特";
        String key = city + " 艺术学校";
        //String key = "北京 酒店";
        String saveFile = "E:\\" + key + ".txt";
        String cookie = "BAIDUID=2313776DFBAA9935A5F6D31C54C5363D:FG=1; BIDUPSID=2313776DFBAA9935A5F6D31C54C5363D; PSTM=1474862723; __cfduid=d23f602ca501a6b3f96739ebe99fa96621488003532; H_WISE_SIDS=115621_102572_104494_117034_117161_117036_104886_107700_103550_109550_107318_117287_117270_116811_117335_117233_117229_117430_117085_116996_117416_116309_115547_116892_116387_117062_115351_115522_116410_116523_110085_114572_117027; BDUSS=jhLODQ5R1F1MVlBSkg4YVk5aFF1MllXOEFEdC1OVjBOSjRJN0hOQzlydWpHcEJaSVFBQUFBJCQAAAAAAAAAAAEAAADbxVUWtqO1sdChxKe51jI5AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKONaFmjjWhZU; validate=34404; BCLID=3814699333811655465; BDSFRCVID=ZxKsJeC62R1-DDvZH2RCJRxZWNaACq7TH6aoL_2AjrkTiXOTEmCLEG0PfU8g0Ku-VIJMogKK0mOTHUcP; H_BDCLCKID_SF=tR4f_K8yfCt3qR5gMJ5q-n3HKUrL5t_XbI6y3JjOHJOoDDvg0xR5y4LdjG5QtfoyQNnzb-3V3pcCJMc5bURvypTy3-Aq5l5j3gTqabb9tU-hqlc8DxDhQfbQ0-6uqP-jW5IL0hF-Kb7JOpkxhfnxyhLS0aCqt6-qfRIJV-cSHJT5jb7x-P7Hh4-tqxby26nkQbbeaJ5nJD_MjRckKn5A0n-tQ-6NhpTU523iaMFbQpP-HfQoKxn_Mb-e-f6qt5chW66fKl0MLnQlbb0xyn_VD4Kf-xnMBMPjamOnaU5JLIFKhD-6D5DMD6PXbfO0btQ2bPoeBR6tK6rMHJ7P-tP_-P4DeN_j-nJZ5mAqof_h-nCWVxcb5p_hylDbWRJbXlv-tgOnaIQqWx5k8nvn-U5MeJ-HhMrvbMv43bRT0tPy5KJvfJo5MtcGhP-UyN3LWh37QC7TVDKbfC8BhI-r5tTEhP6D-Uv05-PX2DPX3b7EfhjHb-O_bf--DRD_DtobthTgbaItafoyyf3xOtjzM6Jxy5K_3xA8Lnvz0a7KXl543tTpelOHQT3mb45bbN3i-Crv-TTnWb3cWKJV8UbS3tbme6jLjatjtj0Hf5r-0nT-5ROMK4OYht5HqR0_hU5K2K62aKDs_CJ2-hcqEIL406Q2hjkwWRbfL6QZLjbz-bbwynK28UbSj4Qz3fIgMUb23J08bKoQBxnbWp5nhMJI257JDMP0-RJ02loy523ion6vQpnlHUtuDjLBD6O0jNR3hC6LK6Ra34_8Kb7VbUc9ynbkbfJBDRKDex3RbHQ7ofonWfoWox38-fQTKj-7yajK2M7dte6BQJvm2xncSh5zLUvpQT8rbR_OK5Oib4jpalczab3vOIJzXpO13ttrexbH55uDtbPD3e; PSINO=2; H_PS_PSSID=; M_LG_UID=374719963; M_LG_SALT=6ba79587925f03a537a3b365d5866e20; MCITY=-131%3A";
        getInfoFromBaiduMap(saveFile, key, city, cookie);
    }

    /**
     * 从百度地图获取地址和电话
     *
     * @param name
     * @return
     */
    public static void getInfoFromBaiduMap(String saveFile, String name, String city, String cookie) throws Exception {

        File file = new File(saveFile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));


        String encodeName = URLEncoder.encode(name, "utf-8");
        int pn = 0;
        int nn = 0;
        String url = String.format(cityMap.get(city), encodeName, pn, nn);
        System.out.println(url);
        String text = Request.Get(url)
                .addHeader("Cookie", cookie)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                .addHeader("Referer", "http://ditu.amap.com/search?")
                .execute().returnContent().asString();
        System.out.println(text);

      /*  String result = "{" + text.substring(text.indexOf("\"result\""), text.indexOf("\"damoce\"") - 1) + "}";
        System.out.println(result);
        String contentStr = "{" + text.substring(text.indexOf("\"content\""), text.indexOf("\"current_city\"") - 1) + "}";
        System.out.println(contentStr);*/


        BaiDuRespContent2 resultBean = gson.fromJson(text, BaiDuRespContent2.class);


        int total = resultBean.getResult().getTotal();
        System.out.println("总记录：" + total);

        int page = total / 10 + 1;
        System.out.println("总页数" + page);
        for (int i = 0; i < page; i++) {
            //System.out.println("当前页" + i);
            pn = i;
            nn = 10 * i;
            url = String.format(cityMap.get(city), encodeName, pn, nn);
            //System.out.println(url);
            text = Request.Get(url)
                    .addHeader("Cookie", cookie)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                    .addHeader("Referer", "http://ditu.amap.com/search?")
                    .execute().returnContent().asString();

            //System.out.println(text);
            resultBean = gson.fromJson(text, BaiDuRespContent2.class);
            List<BaiDuRespContent2.ContentBean> content = resultBean.getContent();

            if (content != null && content.size() > 0) {
                for (int m = 0; m < content.size(); m++) {
                    BaiDuRespContent2.ContentBean curContent = content.get(m);
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
     * @return[
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
