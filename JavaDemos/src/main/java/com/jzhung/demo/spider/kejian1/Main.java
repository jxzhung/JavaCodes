package com.jzhung.demo.spider.kejian1;

import com.jzhung.demo.core.util.RedisUtil;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2016/11/25.
 */
public class Main {
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";
    public static final String BASE_URL = "http://www.1kejian.com";
    public static final String SAVE_DIR = "E:\\data\\课件\\";
    public static String localtionList = "localtionList";
    public static String dowlloadedList = "dowlloadedList";

    private static final int WAIT_TIME = 10000;

    private List<String> urlList = new ArrayList<String>();
    private List<String> softIdList = new ArrayList<String>();
    private List<DownloadJob> downloadJobList = new ArrayList<DownloadJob>();
    private Jedis redis;

    public static void main(String[] args) {
        Main main = new Main();
        main.redis = RedisUtil.getJedis();

        main.getList();

        //main.softIdList.add("169906");
        main.createDowndownJob();
        //DownloadJob job = new DownloadJob();
        //job.softId = "169768";
        //job.downQuery = "softid=169768&downid=0&id=169929&rnd=8100";
        //main.downloadJobList.add(job);
        //main.downloadFile();
    }

    private String getNextListPage(int lessonId, int bookId, int page, int max) {
        if (page == max) {
            return null;
        }
        if (page == 1) {
            return BASE_URL + "/edu/" + lessonId + "/" + bookId;
        }
        return BASE_URL + "/edu/" + lessonId + "/" + bookId + "/kejian_" + bookId + "_" + page + ".html";
    }

    private void getList() {
        int lessonId = 2;
        int bookId = 34;
        int page = 11;
        int max = 36;

        String url = null;
        while ((url = getNextListPage(lessonId, bookId, page, max)) != null) {
            //String url = BASE_URL + "/edu/2/34/kejian_34_3.html";
            try {
                //String content = Request.Get(url).userAgent(UA).execute().returnContent().asString(Charset.forName("gb2312"));
                //Document doc = Jsoup.parse(new File("E:\\t.html"), "gb2312");
                Document doc = Jsoup.connect(url).userAgent(UA).get();
                Elements ems = doc.getElementById("list_m").select("a[href]");
                for (Element e : ems) {
                    String href = BASE_URL + e.attr("href");
                    if (href.contains("/edu")) {
                        urlList.add("href");
                        String softId = href.substring(href.lastIndexOf("/") + 9, href.lastIndexOf("."));
                        softIdList.add(softId);
                        System.out.println("添加：" + href + " ID：" + softId + " 列表大小:" + softIdList.size());
                    }
                }
                //System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            page = page + 1;
        }


    }

    /**
     * 建立下载任务
     */
    private void createDowndownJob() {
        int softListSize = softIdList.size();
        try {
            for (int i = 0; i < softListSize; i++) {
                String softId = softIdList.get(i);
                String url = "http://www.1kejian.com/edu/softdown.asp?softid=" + softId;
                String content = Request.Get(url).userAgent(UA).execute().returnContent().asString(Charset.forName("gb2312"));

                //System.out.println(content);

                Element rndEm = Jsoup.parse(content).getElementById("rnd");
                if (rndEm == null) {
                    System.out.println("无下载地址 跳过:" + url);
                    continue;
                }

                Element forma = rndEm.parent().child(5);
                String down_html = forma.outerHtml();
                String id = down_html.substring(down_html.lastIndexOf("_") + 1, down_html.indexOf("').submit"));
                String rdn = down_html.substring(down_html.lastIndexOf("rnd').value='") + 13, down_html.indexOf(";document.getElementById('form") - 1);
                //System.out.println(id);
                //System.out.println(rdn);

                String downQuery = "softid=" + softId + "&downid=0&id=" + id + "&rnd=" + rdn;

                System.out.println("新建任务：" + i + "/" + softListSize + " " + url + "\n参数:" + downQuery);

                DownloadJob job = new DownloadJob();
                job.downQuery = downQuery;
                job.softId = softId;
                downloadJobList.add(job);
                downloadFile(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     */
    private void downloadFile(DownloadJob job) {
        try {

            String fileName = job.softId + ".rar";

            File saveFile = new File(SAVE_DIR + fileName);
            if(saveFile.exists()){
                System.out.println("已存在文件 跳过：" + saveFile.getAbsolutePath());
                return;
            }

            //for (int i = 0; i < downloadJobList.size(); i++) {
            //DownloadJob job = downloadJobList.get(i);
            String postUrl = "http://www.1kejian.com/edu/download.asp";
            String referer = "http://www.1kejian.com/edu/softdown.asp?softid=" + job.softId;
            Response downPageResp = Request.Post(postUrl)
                    .bodyString(job.downQuery, ContentType.APPLICATION_FORM_URLENCODED)
                    .addHeader("Referer", referer)
                    .userAgent(UA)
                    .execute();
            HttpResponse httpResponse = downPageResp.returnResponse();
            Header head = httpResponse.getFirstHeader("Location");
            System.out.println("获取重定向文件:" + job.downQuery);
            // System.out.println(httpResponse.toString());
            if (head == null) {
                if(job.retry <=3 ){
                    System.out.println("无Location，10秒后重试");
                    TimeUnit.MILLISECONDS.sleep(10000);
                    job.retry = job.retry + 1;
                    downloadFile(job);
                    return;
                }else {
                    System.out.println("达到最大次数，跳过无Location的");
                    System.out.println(httpResponse.toString());
                    return;
                }
            }
            long start = System.currentTimeMillis();
            String path = head.getValue();
            if(!path.startsWith("/edu/UploadFile")){
                System.out.println("地址不对：" + path);
                return;
            }
            String location = BASE_URL + path;

            //redis.lpush(localtionList, location);
            //System.out.println("列表长度" + redis.llen(localtionList));

            System.out.println("下载" + location + " 到：" + saveFile.getAbsolutePath());

            Response response = Request.Get(location).userAgent(UA).execute();
            response.saveContent(saveFile);

            long waitTime = WAIT_TIME - (System.currentTimeMillis() - start);
            if (waitTime > 0) {
                System.out.println("等待:" + waitTime);
                TimeUnit.MILLISECONDS.sleep(waitTime);
            }

            /*HttpResponse fileResp = response.returnResponse();
            HttpEntity entity = fileResp.getEntity();
            long fileLen = entity.getContentLength();
            InputStream in = entity.getContent();
            FileOutputStream fout = new FileOutputStream(saveFile);*/

            /*Header fileLength = response.returnResponse().getFirstHeader("Content-Length");
            if (Integer.valueOf(fileLength.getValue()) == saveFile.length()) {
                System.out.println("下载完成大小正确");
            }*/
            //}


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

