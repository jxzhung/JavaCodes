package com.jzhung.app.traffic.train12306;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jzhung on 2016/12/27.
 */
public class RemainTicket {
    static String API_REMAIN_TICKET = "https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=2017-02-01&leftTicketDTO.from_station=TEK&leftTicketDTO.to_station=VNP&purpose_codes=ADULT";
    static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";

    public static void main(String[] args) {
        new RemainTicketThreaad().start();
    }

    static class RemainTicketThreaad extends Thread {
        @Override
        public void run() {
            CloseableHttpClient httpClient = HttpsSSLClient.createSSLInsecureClient();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(2000)
                    .setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000).build();

            HttpGet getMethod = new HttpGet(API_REMAIN_TICKET);
            getMethod.setConfig(requestConfig);
            getMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

            boolean isRun = true;
            try {
                while (isRun) {
                    // String content = Request.Get(API_REMAIN_TICKET).userAgent(USER_AGENT).execute().returnContent().asString();
                    //String content = doGet(API_REMAIN_TICKET, "utf-8");
                    String content = doGet("utf-8", httpClient, getMethod);
                    if (content != null && content.length() > 0) {
                        Gson gson = new Gson();
                        RemainTicketResp resp = gson.fromJson(content, RemainTicketResp.class);
                        int status = resp.getHttpstatus();
                        System.out.println("响应：" + status + " " + System.currentTimeMillis());
                        List<RemainTicketResp.DataBean> datas = resp.getData();
                        for (RemainTicketResp.DataBean data : datas) {
                            RemainTicketResp.DataBean.QueryLeftNewDTOBean queryLeftNewDTO = data.getQueryLeftNewDTO();
                            String canWebBuy = queryLeftNewDTO.getCanWebBuy();
                            if(canWebBuy.equals("N")){
                                continue;
                            }
                            String trainCode = queryLeftNewDTO.getStation_train_code();
                            String start_station_name = queryLeftNewDTO.getStart_station_name();
                            String end_station_name = queryLeftNewDTO.getEnd_station_name();
                            String from_station_name = queryLeftNewDTO.getFrom_station_name();
                            String to_station_name = queryLeftNewDTO.getTo_station_name();

                            String start_time = queryLeftNewDTO.getStart_time();
                            String arrive_time = queryLeftNewDTO.getArrive_time();
                            String lishi = queryLeftNewDTO.getLishi();
                            String sale_time = queryLeftNewDTO.getSale_time();
                            String ze_num = queryLeftNewDTO.getZe_num();//二等座
                            StringBuilder builder = new StringBuilder();
                            builder.append("车次：");
                            builder.append(trainCode);
                            builder.append(" 起始站：");
                            builder.append(from_station_name);
                            builder.append(" 目标站：");
                            builder.append(to_station_name);
                            builder.append(" 发车时间：");
                            builder.append(start_time);
                            builder.append(" 到达时间：");
                            builder.append(arrive_time);
                            builder.append(" 耗时：");
                            builder.append(lishi);
                            builder.append(" 购买状态：");
                            builder.append(canWebBuy);
                            builder.append(" 开卖时间：");
                            builder.append(sale_time);
                            builder.append(" 二等座余票：");
                            builder.append(ze_num);
                            System.out.println(builder.toString());
                        }

                    }else{
                        System.out.println("响应为空");
                    }
                    Thread.sleep(2000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public static String doGet(String url, String charSet) throws Exception {
        // When an instance CloseableHttpClient is no longer needed and is about to go out
        // of scope the connection manager associated with it must be shut down by calling the
        // CloseableHttpClient.close() method.

        CloseableHttpClient httpClient = null;
        if (url.startsWith("https")) {
            httpClient = HttpsSSLClient.createSSLInsecureClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpGet getMethod = new HttpGet(url);
        return doGet(charSet, httpClient, getMethod);
    }

    private static String doGet(String charSet, CloseableHttpClient httpClient, HttpGet getMethod)
            throws Exception {
        if (charSet == null || charSet.length() == 0) {
            charSet = "UTF-8";
        }
        CloseableHttpResponse response = null;
        HttpEntity responseEntity = null;
        try {
            response = httpClient.execute(getMethod);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state != HttpStatus.SC_OK) {
                throw new Exception("响应编码:" + state);
            }
            responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity, charSet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
