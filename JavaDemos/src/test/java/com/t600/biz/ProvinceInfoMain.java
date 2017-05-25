package com.t600.biz;

import com.t600.biz.util.BizConst;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * 获取全国省份信息
 * Created by Jzhung on 2017/5/19.
 */
public class ProvinceInfoMain {

    @Test
    public void getProviceInfo() {
        String url = "http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html";
        try {
            Document doc = Jsoup.connect(url).userAgent(BizConst.ua)
                    .header("Referer", "http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/")
                    .header("Cookie", "AD_RS_COOKIE=20083361; _gscu_1771678062=95170550jx724z61; _gscbrs_1771678062=1; _trs_uv=2rb_6_j2vdxwhl")
                    .get();

            Elements ps = doc.select("p[class=MsoNormal]");

            String saveDir = "C:\\Users\\Jzhung\\Desktop\\教育局信息\\省份采集";

            String last = null;
            for (Element p : ps) {
                String line = p.text().replace(" ", "-").replace("　", "-");
                line = line.substring(line.indexOf(" "), line.length()).replace(" ", "");
                //line = line.replace("-", " ");
                if (line.contains("市辖区")) {
                    line = last;
                } else {
                    last = "-" + line;
                }
                //System.out.println(line);

                if (line.startsWith("---")) {
                    //写入县级单位
                    line = line.replace("---", "") + "教育局";
                    continue;
                } else if (line.startsWith("--")) {
                    //写入市级单位
                    line = line.replace("--", "##");

                } else if (line.startsWith("-")) {
                    //建立文件
                    line = line.replace("-", "#");
                }
                System.out.println(line);
               /* Element province = p.child(p.childNodeSize() - 1);
                System.out.println(province.text().trim().replace("　", ""));*/
            }
            //System.out.println(doc.text());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
