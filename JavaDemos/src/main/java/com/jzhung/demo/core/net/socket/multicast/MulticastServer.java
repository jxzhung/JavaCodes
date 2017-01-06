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
        try {
            FileInputStream fin = new FileInputStream(imgFile);
            byte[] fileByte = inputStreamToByteArray(fin);
            System.out.println("文件长度" + fileByte.length);
            while (true) {
                DatagramPacket packet = null;
                byte[] data = new byte[1024];
                byte[] fileLen = intToBytes(fileByte.length);

                // 数据总长 4 序号 2 数据长度 3
                System.arraycopy(fileLen, 0, data, 0, fileLen.length);

                int i = 0;
                while (i < data.length){
                    //System.arraycopy(fileByte, 0, data, 4, fileByte.length);

                    packet = new DatagramPacket(data, data.length, group, port);
                    mutiSocket.send(packet);
                }

                byte[] screenBytes = getScreen();


                System.arraycopy(screenBytes, 0, data, 1 + fileLen.length, (data.length - (1 + fileLen.length)));

                packet = new DatagramPacket(data, data.length, group, port);
                mutiSocket.send(packet);


                screenBytes = ("图片长度：" + screenBytes.length).getBytes();
                packet = new DatagramPacket(screenBytes, screenBytes.length, group, port);
                mutiSocket.send(packet);
                sleep(2000);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static byte[] intToBytes(int value) {
        byte[] byte_src = new byte[4];
        byte_src[3] = (byte) ((value & 0xFF000000) >> 24);
        byte_src[2] = (byte) ((value & 0x00FF0000) >> 16);
        byte_src[1] = (byte) ((value & 0x0000FF00) >> 8);
        byte_src[0] = (byte) ((value & 0x000000FF));
        return byte_src;
    }

    public static int bytesToInt(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF)
                | ((ary[offset + 1] << 8) & 0xFF00)
                | ((ary[offset + 2] << 16) & 0xFF0000)
                | ((ary[offset + 3] << 24) & 0xFF000000));
        return value;
    }

    public static byte[] inputStreamToByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
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
