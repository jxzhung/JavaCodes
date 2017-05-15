package com.jzhung.demo.socket.multicast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * 控制服务
 * Created by Jzhung on 2017/5/9.
 */
public class CmdSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * 在Java UDP中单播与广播的代码是相同的,要实现具有广播功能的程序只需要使用广播地址即可, 例如：这里使用了本地的广播地址
         */
        InetAddress inetAddr = InetAddress.getByName("255.255.255.255");
        DatagramSocket client = new DatagramSocket();


        ServerNode serverNode = new ServerNode();
        serverNode.name = "屏幕录像";
        serverNode.desc = "提供图像实时回传服务";
        serverNode.ip = "192.168.1.216";
        serverNode.port = 8888;
        serverNode.protocol = "ftp";

        Gson gson = new Gson();
        String json = gson.toJson(serverNode);
        byte[] msg = json.getBytes();

        while (true){
            /*String m = "信息" + System.currentTimeMillis();
            byte[] msg = new String(m).getBytes();*/
            DatagramPacket sendPack = new DatagramPacket(msg, msg.length, inetAddr, 8888);

            client.send(sendPack);
            System.out.println("发送 " + json);
            TimeUnit.SECONDS.sleep(1);
        }

        //client.close();
    }

    static class ServerNode{
        String name;
        String desc;
        String ip;
        int port;
        String protocol;
    }
}
