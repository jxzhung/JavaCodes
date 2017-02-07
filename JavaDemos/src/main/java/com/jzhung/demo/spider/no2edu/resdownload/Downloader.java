package com.jzhung.demo.spider.no2edu.resdownload;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * Created by Jzhung on 2017/2/7.
 */
public class Downloader {
    String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    String cookie = "mem[rank]=70; mem[exptime]=30; mem[userid]=wangtao5566; __jsluid=68f303b219642134aaa220afd53195c3; DedeUserID=2747185; DedeUserID__ckMd5=859c06162e7ad9f5; DedeLoginTime=1486456690; DedeLoginTime__ckMd5=61ea812700f040e7; UCenter_uid=2720858; UCenter_username=wangtao5566; Example_auth=9b64KEUNqAKIXZzl6YTtQMzo0veGKVdv%2FBQSMq6IJcxCbtAJo5bLsjW8fx27TpNk; PHPSESSID=qnt9mtlm7jd91r56r87ge8n9j3";

    public static void main(String[] args) {
        Downloader downloader = new Downloader();
        String url = "http://club.dearedu.com/member/down_gb.php?zid=4188346&price=3";
        String destFile = "e:\\3";
        Unit unit = new Unit();
        unit.url = "http://s.dearedu.com/list.php?";
        downloader.downFile(url, destFile);
    }



    public void downFile(String url, String destFile) {
        try {

            // 定义HttpClient
            CloseableHttpClient client = HttpClients.createDefault();
            // Get
            HttpGet request = new HttpGet();
            request.setHeader("Cookie", cookie);
            request.setHeader("User-Agent", userAgent);
            request.setURI(URI.create(url));
            request.setConfig(createConfig(5000, false));

            //第1次重定向的地址
            HttpResponse httpResponse = client.execute(request);
            Header h = httpResponse.getFirstHeader("Location");
            if (h == null) {
                System.out.println("重定向地址1无地址");
                return;
            }
            String redirectUrl1 = h.getValue();
            //System.out.println("        重定向地址：" + redirectUrl1);
            request.setURI(URI.create(redirectUrl1));

            //第2次重定向获取真实地址
            HttpResponse httpResponse2 = client.execute(request);
            Header h2 = httpResponse2.getFirstHeader("Location");
            if (h2 == null) {
                System.out.println("重定向地址2无地址");
                return;
            }
            String redirectUrl2 = h2.getValue();


            String fileType = getFileType(redirectUrl2);
            String realFile = destFile + fileType;
            System.out.println("------> 保存到：" + realFile);
            File readDestFile = new File(realFile);
            if (readDestFile.exists()) {
                System.out.println("文件已存在");
                return;
            }
            //redirectUrl2 = URLEncoder.encode(redirectUrl2, "utf-8");
            int index1 = redirectUrl2.lastIndexOf("/");
            String fileName = redirectUrl2.substring(index1 + 1);
            //System.out.println(fileName);
            String newName = URLEncoder.encode(fileName, "utf-8");

            redirectUrl2 = redirectUrl2.substring(0, index1 + 1) + newName;
            System.out.println("        真实文件地址 " + redirectUrl2);
            Request.Get(redirectUrl2)
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Cookie", cookie)
                    .addHeader("User-Agent", userAgent)
                    .execute().saveContent(readDestFile);
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
