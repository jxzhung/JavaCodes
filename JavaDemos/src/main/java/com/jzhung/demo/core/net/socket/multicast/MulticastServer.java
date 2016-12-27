package com.jzhung.demo.core.net.socket.multicast;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastServer extends Thread {
    String message[] = {"失物招领：有谁在操场丢失钥匙一串，请到学校广播站认领。", "大风蓝色预警：预计今天下午有北风6级，请有关单位和人员做好防范准备。"};
    int port = 9876;//组播的端口
    InetAddress group = null;//组播的组地址
    MulticastSocket mutiSocket = null;//组播套接字

    public MulticastServer() {
        try {
            group = InetAddress.getByName("230.198.112.0");//设置广播组地址
            mutiSocket = new MulticastSocket(port);//多点广播套接字将在port端口广播
            mutiSocket.setTimeToLive(1);
            mutiSocket.joinGroup(group);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public void run() {
        File imgFile = new File("E:\\data\\screen", "1482741540817.jpg");

        while (true) {
            try {
                DatagramPacket packet = null;
                for (String msg : message)//循环发送每条广播信息
                {
                    /*byte buff[] = msg.getBytes();
                    packet = new DatagramPacket(buff, buff.length, group, port);
                    System.out.println(new String(buff));
                    mutiSocket.send(packet);*/
                    byte[] data = new byte[1024];
                    data[0] = 1;
                    byte[] fileLen = "固定大小文件名".getBytes();
                    System.arraycopy(fileLen, 0, data, 2, fileLen.length);

                    byte[] screenBytes = getScreen();


                    System.arraycopy(screenBytes, 0, data, 1 + fileLen.length, (data.length - (1 + fileLen.length)));

                    packet = new DatagramPacket(data, data.length, group, port);
                    mutiSocket.send(packet);




                    screenBytes = ("图片长度：" + screenBytes.length).getBytes();
                    packet = new DatagramPacket(screenBytes, screenBytes.length, group, port);
                    mutiSocket.send(packet);
                    sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("Error:" + e);
            }
        }
    }


    public static void main(String[] args) {
        new MulticastServer().start();
    }

    public byte[] getScreen() {
        ByteArrayOutputStream out = null;
        try {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(d.width, d.height));
            out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", out);
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}
