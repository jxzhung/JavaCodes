package com.jzhung.demo.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpServer {
    public static boolean run = true;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            // 建立Socket监听
            ServerSocket server = new ServerSocket(8899);

            while (run) {
                // 请求过来产生一个Socket对象
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    public void run() {
                        //新线程处理请求
                        processRequest(socket);
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求
     * @param socket
     */
    private static void processRequest(Socket socket) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            st.nextToken();
            String url = st.nextToken();

            System.out.println(line);
            System.out.println("请求地址：" + url);


            //响应
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-type:text/html; charset=utf-8");
            pw.println();
            pw.println("<h1>访问成功！</h1>");
            pw.flush();
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}