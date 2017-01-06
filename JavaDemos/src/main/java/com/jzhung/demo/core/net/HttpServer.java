package com.jzhung.demo.core.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jzhung on 2016/12/29.
 */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("输入端口：");
        Scanner in = new Scanner(System.in);
        int port = in.nextInt();
        startServer(port);
    }

    private static void startServer(int port) {
        System.out.println("服务器已启动 端口" + port);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                OutputStream out = socket.getOutputStream();
                String addr = socket.getRemoteSocketAddress().toString();
                String msg = "<a href=\"https://www.hao123.com\">好123</a>Simple HTTP Server via Socket. Server time:" + System.currentTimeMillis();
                System.out.println(addr + "访问服务器");
                System.out.println("返回" + msg + " 长度:" + msg.length());

                PrintWriter writer = new PrintWriter(out);
                writer.println("HTTP/1.1 200 OK");
                writer.println("Connection: keep-alive");
                writer.println("Content-Length:" + msg.length());
                writer.println("Content-Type: text/html; charset=UTF-8");
                writer.println("Date: Thu, 29 Dec 2016 02:33:02 GMT");
                writer.println("Server: openresty");
                writer.println("");
                writer.println("");
                writer.println(msg);

                writer.flush();
                writer.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
