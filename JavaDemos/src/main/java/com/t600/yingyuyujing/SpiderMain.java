package com.t600.yingyuyujing;

import com.google.gson.Gson;
import com.t600.yingyuyujing.beans.LikeListResp;
import com.t600.yingyuyujing.beans.LikeResp;
import com.t600.yingyuyujing.beans.VideoInfoResp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 *
 */
public class SpiderMain {
    private int likeCount;
    private static final String BASE_URL = "https://mobile.meclass.com";
    private static final String LIKE_COUNT_URL = BASE_URL + "/core/lp/wordsvideo/favorite/getCount";
    private static final String LIKE_LIST_URL = BASE_URL + "/core/lp/wordsvideo/favorite/list?count=20&offset=%d";
    private static final String VIDEO_DETAIL_URL = BASE_URL + "/core/lp/words_video/page/user/getVideoInfo?targetWordId=0&videoId=%d";
    private Gson gson = new Gson();
    private static String saveDir = "F:\\英傍语境英语\\";


    public static void main(String[] args) {
        new SpiderMain().start();
    }

    private void start() {
        // 获取收藏总数
        likeCount = getLikeCount();
        System.out.printf("收藏总数：" + likeCount);

        List<LikeListResp.ResultBean> videoList = new ArrayList<LikeListResp.ResultBean>();
        // 分页获取所有视频ID
        for (int i = 0; i < likeCount; i = i + 20) {
            String pageUrl = String.format(LIKE_LIST_URL, i);
            List<LikeListResp.ResultBean> list = getVideoList(pageUrl);
            System.out.println("抓取：" + list.size());
            videoList.addAll(list);
        }

        //String videoListJson = gson.toJson(videoList);
        int totalCount = videoList.size();
        System.out.println("视频总数：" + totalCount);
        // 下载每个视频的信息
        for (int i = 0; i < totalCount; i++) {
            // TODO: 2018/6/8 断点续传检查
            System.out.println("进度：" + (i + 1) + "/" + totalCount);
            downloadVideo(videoList.get(i));
        }
    }

    private void downloadVideo(LikeListResp.ResultBean resultBean) {
        int videoId = resultBean.getId();
        VideoInfoResp videoDetail = getVideoDetail(videoId);
        // 保存元信息
        //String curDir = String.format(saveDir, resultBean.getId());
        String curDir = saveDir;
        String file = curDir + videoId + ".json";
        String json = gson.toJson(videoDetail.getResult());
        saveToFile(file, json);

        // 下载视频
        String videoUrl = videoDetail.getResult().getVideoInfo().getUrl();
        try {
            File videoFile = new File(curDir + videoId + ".mp4");
            if (videoFile.exists() && videoFile.length() > 0) {
                System.out.println("已存在视频：" + videoUrl + " ---> " + videoFile.getAbsolutePath());
                return;
            }
            System.out.println("下载视频：" + videoUrl + " ---> " + videoFile.getAbsolutePath());
            Request.Get(videoUrl).execute().saveContent(videoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VideoInfoResp getVideoDetail(int id) {
        String videoInfoUrl = String.format(VIDEO_DETAIL_URL, id);
        String json = getJson(videoInfoUrl);
        VideoInfoResp videoInfoResp = gson.fromJson(json, VideoInfoResp.class);
        return videoInfoResp;
    }

    private List<LikeListResp.ResultBean> getVideoList(String pageUrl) {
        String json = getJson(pageUrl);
        LikeListResp likeListResp = gson.fromJson(json, LikeListResp.class);
        return likeListResp.getResult();
    }

    private int getLikeCount() {
        String json = getJson(LIKE_COUNT_URL);
        LikeResp likeResp = gson.fromJson(json, LikeResp.class);
        int count = likeResp.getResult();
        return count;
    }

    private String getJson(String url) {
        System.out.println("url:" + url);
        try {
            CloseableHttpClient httpClient = HttpsUtils.getHttpClient();
            HttpGet httpGet = new HttpGet(url);
            addRequestHeader(httpGet);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String content = getHttpEntityContent(response);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void addRequestHeader(HttpGet request) {
        request.addHeader("X-REQUESTED-WITH", "1");
        request.addHeader("platform", "android");
        request.addHeader("version", "22");
        request.addHeader("Accept-Language", "zh-cn");
        request.addHeader("city_id", "110000");
        request.addHeader("Content-Length", "0");
        request.addHeader("Host", "mobile.meclass.com");
        request.addHeader("Cookie", "MCSID=a3a936d0-6927-418a-a022-612d45400e66; keep_login=1366687939SrKSac%2Ba8InAB9%2FeprdvrWOvnGI2s");
        request.addHeader("Cookie2", "$Version=1");
        request.addHeader("Accept-Encoding", "gzip");
    }

    private void addCommonHeader(Request request) {
        request.addHeader("X-REQUESTED-WITH", "1")
                .addHeader("platform", "android")
                .addHeader("version", "22")
                .addHeader("Accept-Language", "zh-cn")
                .addHeader("city_id", "110000")
                .addHeader("Content-Length", "0")
                .addHeader("Host", "mobile.meclass.com")
                .addHeader("Cookie", "MCSID=a3a936d0-6927-418a-a022-612d45400e66; keep_login=1366687939SrKSac%2Ba8InAB9%2FeprdvrWOvnGI2s")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Encoding", "gzip")
                .viaProxy("127.0.0.1:8888");
    }

    /**
     * 获得响应HTTP实体内容
     *
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
        //通过HttpResponse 的getEntity()方法获取返回信息
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            br.close();
            is.close();
            return sb.toString();
        }
        return "";
    }

    public static void saveToFile(String filePath, String content) {
        System.out.println("保存元数据:" + filePath);
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            System.out.println("已存在文件：" + filePath);
            return;
        }
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(content.getBytes("utf-8"));
            fout.flush();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
