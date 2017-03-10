package com.jzhung.server.report;

import com.google.gson.Gson;
import com.jzhung.server.report.bean.AppReport;
import com.jzhung.server.report.bean.ReportConfig;
import com.jzhung.server.report.bean.ReportInfo;
import com.jzhung.server.report.service.IReportService;
import com.jzhung.server.report.service.ReportServiceImpl;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Jzhung on 2017/3/3.
 */
public class WatchrDog {
    private ReportConfig reportConfig;
    private int port = 12345;
    private IReportService reportService;
    private Gson gson = new Gson();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        WatchrDog test = new WatchrDog();
        if (test.initConfig()) {
            test.startServer();
        } else {
            System.out.println("ERROR! Log Dir is not exists or not a directory!");
        }

    }

    public boolean initConfig() {
        Scanner input = new Scanner(System.in);
        String dir = input.nextLine();
        File logDir = new File(dir);
        if (logDir.exists() && logDir.isDirectory()) {
            reportConfig = new ReportConfig();
            reportConfig.setLogDir(dir);
            return true;
        }
        return false;
    }

    public void startServer() {
        reportService = new ReportServiceImpl();

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
                                //保存信息
                                PrintStream ps = new PrintStream(accept.getOutputStream());
                                writeRequestCode(ps, 200);
                                processHttpData(accept, br);
                            }
                            accept.close();
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
     * 解析HTTP数据并保存
     *
     * @param accept
     * @param br
     */
    private void processHttpData(Socket accept, BufferedReader br) {
        String line = null;
        String json = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.contains("\r\n\r\n")) {
                    line = br.readLine();
                    json = line;
                    break;
                }
            }
            //获取到json
            if (json != null && !json.equals("")) {
                AppReport appReport = gson.fromJson(json, AppReport.class);
                InetSocketAddress remote = (InetSocketAddress) accept.getRemoteSocketAddress();
                InetAddress remoteIp = remote.getAddress();
                ReportInfo reportInfo = new ReportInfo();
                reportInfo.setFromIp(remoteIp.getHostAddress());
                reportInfo.setReportTime(sdf.format(new Date()));

                reportService.saveReport(appReport);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 向客户端返回
     *
     * @param ps
     */
    private void writeRequestCode(PrintStream ps, int code) {
        String html = "ok";
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
