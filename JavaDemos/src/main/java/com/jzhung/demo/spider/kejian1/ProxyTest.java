package com.jzhung.demo.spider.kejian1;

import com.google.common.net.HostAndPort;
import com.jzhung.demo.core.util.FileUtil;
import com.jzhung.demo.spider.no2edu.Const;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jzhung on 2016/11/28.
 */
public class ProxyTest {
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";

    @Test
    public void testProxyIp(){
        String url = "http://ip.chinaz.com/";
        List<HttpHost> hpList = getProxyHosts();
        for (HttpHost proxy:hpList
             ) {
            System.out.println("测试" + proxy.getHostName() + ":" + proxy.getPort());
            try {
                String result = Request.Get(url).userAgent(UA).viaProxy(proxy).connectTimeout(3000).socketTimeout(3000).execute().returnContent().asString();
                Document doc = Jsoup.parse(result);
                Element ipInfo = doc.getElementById("rightinfo");
                if(ipInfo != null){
                    System.out.println(ipInfo.text());
                }
                //System.out.println(result);
            } catch (IOException e) {
                System.out.println("异常" + proxy.getHostName());
            }
        }

    }

    public List<HttpHost> getProxyHosts(){
        String ipFile = "D:\\free20161.txt";
        String ipContent = FileUtil.getFileContent(ipFile);
        StringTokenizer st = new StringTokenizer(ipContent, "\n");
        List<HttpHost> hpList = new ArrayList<HttpHost>();
        while (st.hasMoreTokens()){
            String ipStr = st.nextToken();
            int index = ipStr.indexOf(":");
            String ip = ipStr.substring(0, index);
            int port = Integer.valueOf(ipStr.substring(index + 1, ipStr.length()));
            HttpHost host = new HttpHost(ip, port);
            hpList.add(host);
        }
        return hpList;
    }
}
