package com.jzhung.demo.spider.ks5u;

import com.jzhung.demo.core.util.DocumentUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by Jzhung on 2017/2/10.
 */
public class Main {
    String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    static String cookie = "menu=1; dheight=360; CNZZDATA2554952=cnzz_eid%3D2038113098-1486517757-null%26ntime%3D1486691529; CNZZDATA250983=cnzz_eid%3D713030791-1486519024-null%26ntime%3D1486691693; CNZZDATA1253224608=1280583560-1486517648-http%253A%252F%252Fwww.ks5u.com%252F%7C1486631710; CNZZDATA5371488=cnzz_eid%3D344029585-1486634565-http%253A%252F%252Fwww.ks5u.com%252F%26ntime%3D1486634565; CNZZDATA4161226=cnzz_eid%3D1242562739-1486635005-http%253A%252F%252Fwww.ks5u.com%252F%26ntime%3D1486635005; acw_tc=AQAAAOquuwa7fgwAXJRsO4wXmmeo1R2/; ASPSESSIONIDSQQQRATR=ODPGCOOBHENBFLMKIFMAHGAA; ASPSESSIONIDSSTRQBSQ=FCIHPMOBFDOHHAEIOOFJIPHK; views%5FResIdList=1776086%2C1773906%2C142260%2C2168379%2C; ASPSESSIONIDQQQQQDSQ=FEAJOMOBEFECMJOMBADBIFGI";
    static String referer = "http://www.ks5u.com/";
    String saveDir;

    public static void main(String[] args) throws IOException {
        Main task = new Main();
        String beginUrl = "http://www.ks5u.com/shiti/gaokao/2015/";
        task.saveDir = "E:\\资源库\\2015试题\\";
        task.getResList(beginUrl);


       /* String url = "http://www.ks5u.com/shiti/gkst.asp";
        String content = Request.Get(url).execute().returnContent().asString();
        System.out.println(content);*/
        /*String resUrl = "http://www.ks5u.com/USER/INC/DownCom.asp?id=2169337";
        downRes(resUrl);*/
    }

    private void getResList(String beginUrl) {
        Document doc = DocumentUtil.getDoc(beginUrl);
        //System.out.println(doc);
        Element contentDiv = doc.getElementById("container");
        Elements allLinks = contentDiv.select("a[href]");
        //遍历所有超链接
        for (int i = 0; i < allLinks.size(); i++) {
            Element link = allLinks.get(i);
            String text = link.text();
            if (!text.equals("解析")) {
                continue;
            }
            String href = link.attr("href");
            String id = href.substring(href.lastIndexOf("/") + 1).replace(".shtml", "");
            System.out.println(text + href);
            System.out.println(id);
            if (id == null || id.equals("") || id.length() < 3) {
                continue;
            }
            String downUrl = "http://www.ks5u.com/USER/INC/DownCom.asp?id=" + id;

            downFile(downUrl, saveDir);
        }
    }


    public void downFile(String url, String destFile) {
        try {
            // 定义HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // Get
            HttpGet request = new HttpGet();
            request.setHeader("Cookie", cookie);
            request.setHeader("User-Agent", userAgent);
            request.setHeader("Referer", referer);
            request.setURI(URI.create(url));
            request.setConfig(createConfig(5000, false));

            //第1次重定向的地址
            HttpResponse httpResponse = httpClient.execute(request);
            Header h = httpResponse.getFirstHeader("Location");
            if (h == null) {
                System.out.println("重定向地址1无地址");
                return;
            }
            String redirectUrl1 = h.getValue();
            System.out.println("        重定向地址1：" + redirectUrl1);


            //第2次重定向获取真实地址
            request.setURI(URI.create(redirectUrl1));
            HttpResponse httpResponse2 = httpClient.execute(request);
            Header h2 = httpResponse2.getFirstHeader("Location");
            if (h2 == null) {
                System.out.println("重定向地址2无地址");
                return;
            }
            String redirectUrl2 = h2.getValue();

            System.out.println("        重定向地址2：" + redirectUrl2);

            Response execute = Request.Get(redirectUrl2).addHeader("Cookie", cookie)
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Referer", referer).execute();
            HttpResponse resp = execute.returnResponse();
            Header[] headers = resp.getHeaders("Content-Disposition");
            Header header = headers[0];
            String nameStr = new String(header.getValue().getBytes("ISO-8859-1"), "gb2312");

            String fileName = nameStr.substring(nameStr.indexOf("=") + 1).replace(" ", "").replace("www.ks5u.com", "");
            String saveFile = destFile + fileName;
            System.out.println(saveFile);

            FileOutputStream fout = new FileOutputStream(new File(saveFile));
            resp.getEntity().writeTo(fout);
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RequestConfig createConfig(int timeout, boolean redirectsEnabled) {
        return RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setRedirectsEnabled(redirectsEnabled)
                .build();
    }

    /**
     * 文件类型含点
     *
     * @param file
     * @return
     */
    public static String getFileType(String file) {
        return file.substring(file.lastIndexOf("."), file.length());
    }

}
