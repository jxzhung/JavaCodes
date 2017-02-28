package com.t600.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jzhung on 2017/2/27.
 */
public class AlipayTest {
    private int port = 666;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        AlipayTest test = new AlipayTest();
        test.startServer();
    }

    private void startServer() {
        try {
            final ServerSocket server = new ServerSocket(port);
            while (true) {
                final Socket accept = server.accept();
                new Thread(new Runnable() {
                    public void run() {
                        InputStream is = null;
                        try {
                            is = accept.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String line = br.readLine();

                            //排除浏览器请求的favicon.ico
                            if (line.contains("favicon.ico")) {
                                accept.close();
                            } else {
                                PrintStream ps = new PrintStream(accept.getOutputStream());
                                writeRequestCode(ps, 200);
                                doHttpRequest();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送模拟回调请求
     */
    private void doHttpRequest() {
        String url = "http://192.168.1.163:80/alipay/callbacks.action";
        String data = "{\"body\":[\"高考提分派一年会员\"],\"subject\":[\"高考提分派一年会员\"],\"sign_type\":[\"RSA2\"],\"buyer_logon_id\":[\"nbv***@sandbox.com\"],\"auth_app_id\":[\"2016080400166827\"],\"notify_type\":[\"trade_status_sync\"],\"out_trade_no\":[\"20170227101627\"],\"point_amount\":[\"0.00\"],\"version\":[\"1.0\"],\"fund_bill_list\":[[{\"amount\":\"1000.00\",\"fundChannel\":\"ALIPAYACCOUNT\"}]],\"passback_params\":[\"studentId=1991601693&commStudentId=\"],\"buyer_id\":[\"2088102170220456\"],\"total_amount\":[\"1000.00\"],\"trade_no\":[\"2017022721001004450200141907\"],\"notify_time\":[\"2017-02-27 10:16:42\"],\"charset\":[\"utf-8\"],\"invoice_amount\":[\"1000.00\"],\"gmt_payment\":[\"2017-02-27 10:16:41\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"sign\":[\"MOQgakPnUs/Y8DXOPA2li7piZSoACsaxJJIpBxbpVPtFztwAoj9OvikEvxgFXaPz0UMd8as3wZi84ehwku0kvbpvdIaLOatHfEp8pU6WpRSUaxeTGKgD7+ytClBEGBp2wMTVTDBAq+tg+tzqnrwmDNFQySD4u/LljgoGyZLyv1pE9Ijhg0fSz/BTSTDKjdyLM8K1z8H5SIlfjL6Kt5gMiAqsNnOR7WeR2rBH5+6idpr79PAGtE0rNxzezhzkkORdszLOVaCpzgILF/Okp0L6HNx0vYa3D6cM9C247asB+ZfPEBLLYyB9m4S9RiN+gW6UNyphXFFFRFayGCKRhbe/TA==\"],\"gmt_create\":[\"2017-02-27 10:16:40\"],\"buyer_pay_amount\":[\"1000.00\"],\"receipt_amount\":[\"1000.00\"],\"seller_id\":[\"2088102169942831\"],\"app_id\":[\"2016080400166827\"],\"seller_email\":[\"wnkedc2971@sandbox.com\"],\"notify_id\":[\"1571e8c3e6797dc6a3a75e6415768a5jh2\"]}";
        try {
            HttpResponse httpResponse = Request.Post(url).bodyString(data, ContentType.TEXT_HTML).execute().returnResponse();
            int code = httpResponse.getStatusLine().getStatusCode();
            System.out.println("--" + code);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 向客户端返回
     *
     * @param ps
     */
    private void writeRequestCode(PrintStream ps, int code) {
        String time = sdf.format(new Date());
        String html = "<html><head><title>" + code + "</title></head><body><h1>code:" + code + "</h1>" + time + "</body></html>";
        writeHeader(ps, code + "", "Content-Type: text/html", html.length());
        ps.println();
        try {
            ps.print(new String(html.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向客户端写响应头部
     *
     * @param ps
     * @param status
     * @param contentType
     * @param contentLen
     */
    private void writeHeader(PrintStream ps, String status, String contentType, int contentLen) {
        ps.println("HTTP/1.1 " + status);
        ps.println("Server: HttpServer_by_jzhung");
        ps.println("Content-Type:" + contentType); // 返回的类型
        ps.println("Content-length:" + contentLen);
        ps.println("Connection: close");// 请求完毕立即断开连接
        ps.println("Allow: GET");
        ps.println("Expires: 0");
    }
}
