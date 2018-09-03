package com.jzhung.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class CrackUtil {

    public static void main(String[] args) {
        System.out.println("first you must add 127.0.0.1 d.tcpspeed.com to your server hosts" +
                "start this when execute tcpspeed start/restart/activate/check_activation etc.");
        startRegisterServer();
    }

    private static void startRegisterServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true) {

                final Socket accept = serverSocket.accept();
                new Thread(new Runnable() {
                    public void run() {
                        processRequst(accept);
                    }

                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRequst(Socket socket) {
        InputStream sin;

        try {
            sin = socket.getInputStream();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(sin));
            String line = null;
            //String ip = socket.getInetAddress().getHostAddress();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            String ip = remoteSocketAddress.toString().replace("/", "");
            ip = ip.substring(0, ip.indexOf(":"));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // active shell检测
                if (line.contains("/xs/active.jsp?type=check_rrttc")) {
                    printWriter.write("HTTP/1.1 200 OK\r\n");
                    printWriter.write("Server: Resin/4.0.36\r\n");
                    printWriter.write("Cache-Control: private\r\n");
                    printWriter.write("Set-Cookie: JSESSIONID=aaazXgEfrMJiy_fUZehHv; path=/\r\n");
                    printWriter.write("Content-Type: text/html; charset=utf-8\r\n");
                    String content = "\r\n\r\n\r\n\r\n\r\n\r\n{\"code\":1}\r\n";
                    printWriter.write("Content-Length: " + content.length() + "\r\n\n");
                    printWriter.write(content);
                    //check shell检测
                } else if (line.contains("/xs/active.jsp?type=check_active_text")) {
                    printWriter.write("HTTP/1.1 200 OK\r\n");
                    printWriter.write("Server: Resin/4.0.36\r\n");
                    printWriter.write("Cache-Control: private\r\n");
                    printWriter.write("Set-Cookie: JSESSIONID=aaazXgEfrMJiy_fUZehHv; path=/\r\n");
                    printWriter.write("Content-Type: text/html; charset=utf-8\r\n");
                    String content = "\r\n\r\n\r\n\r\n\r\n\r\n" + "IP " + ip + " activated,the license will expire on 2066.10.27\r\n";
                    printWriter.write("Content-Length: " + content.length() + "\r\n\n");
                    printWriter.write(content);

                    //type=live 使用时定时验证
                }else if (line.contains("/xs/active.jsp?type=live")) {
                    printWriter.write("HTTP/1.1 200 OK\r\n");
                    printWriter.write("Server: Resin/4.0.36\r\n");
                    printWriter.write("Cache-Control: private\r\n");
                    printWriter.write("Set-Cookie: JSESSIONID=aaazXgEfrMJiy_fUZehHv; path=/\r\n");
                    printWriter.write("Content-Type: text/html; charset=utf-8\r\n");
                    String content = "\r\n\r\n\r\n\r\n\r\n\r\n\n";
                    printWriter.write("Content-Length: " + content.length() + "\r\n\n");
                    printWriter.write(content);

                    //type=submit_code shell激活
                } else {
                    printWriter.write("HTTP/1.1 200 OK\r\n");
                    printWriter.write("Server: Resin/4.0.36\r\n");
                    printWriter.write("Cache-Control: private\r\n");
                    printWriter.write("Set-Cookie: JSESSIONID=aaazXgEfrMJiy_fUZehHv; path=/\r\n");
                    printWriter.write("Content-Type: text/html; charset=utf-8\r\n");
                    String content = "\r\n\r\n\r\n\r\n\r\n\r\n" + "IP " + ip + " activated\r\n";
                    printWriter.write("Content-Length: " + content.length() + "\r\n\r\n");
                    printWriter.write(content);
                }
                printWriter.flush();
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
