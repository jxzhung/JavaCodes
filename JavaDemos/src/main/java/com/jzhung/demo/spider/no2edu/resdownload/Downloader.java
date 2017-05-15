package com.jzhung.demo.spider.no2edu.resdownload;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by Jzhung on 2017/2/7.
 */
public class Downloader {
    String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    String cookie = "__jsluid=6729719a7ea58e95ae993b9e407aed04; UM_distinctid=15ba8db71ccdb0-06bde5956139ef-4e47052e-232800-15ba8db71cdce0; DedeUserID=8898237; DedeUserID__ckMd5=4c33247920b82842; OkHW_2132_saltkey=GxzLBvJX; OkHW_2132_lastvisit=1493872058; OkHW_2132_sid=RaR6r6; OkHW_2132_lastact=1493875658%09uc.php%09; OkHW_2132_auth=ddceVMlwU9M79xzNB7NBbDfCS8priFw1G%2FpYi52pGMdQqV%2F2dkbZrJPLUa6hId6PsBOwhMTHeajMPIw3yF%2Bl2qh%2Fg7Lt; UCenter_uid=2734227; UCenter_username=suntao0468; Example_auth=9e08ei%2BNlOD6aTbWse8xH%2B2KH2Xl44GM5AVdoObBhjH7yCJR8MuWb9zbE3ALoFI; CNZZDATA5677110=cnzz_eid%3D651568479-1493185932-http%253A%252F%252Fclub.dearedu.com%252F%26ntime%3D1493872425; CNZZDATA1257122261=1075150133-1493876112-http%253A%252F%252Fwww.dearedu.com%252F%7C1493876112; DedeLoginTime=1493883056; DedeLoginTime__ckMd5=36bc46ebb2d17e05";//todo 替换为登陆后的Cookie

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
                    .execute().saveContent(readDestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downFileRirectly(String url, String destFile) {
        System.out.println("下载视频：" + url);
        File readDestFile = new File(destFile);
        if (readDestFile.exists()) {
            System.out.println("文件已存在 " + destFile);
            return;
        }
        try {
            Request.Get(url)
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Cookie", cookie)
                    .addHeader("User-Agent", userAgent)
                    .execute().saveContent(new File(destFile));
            System.out.println("下载完毕 存储位置：" + destFile);
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
