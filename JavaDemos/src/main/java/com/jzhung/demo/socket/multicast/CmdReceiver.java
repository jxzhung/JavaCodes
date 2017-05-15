package com.jzhung.demo.socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * Created by Jzhung on 2017/5/9.
 */
public class CmdReceiver {

    public static void main(String[] args) throws IOException
    {

        DatagramPacket receive = new DatagramPacket(new byte[1024], 1024);
        DatagramSocket server = new DatagramSocket(8888);

        System.out.println("---------------------------------");
        System.out.println("Server current start......");
        System.out.println("---------------------------------");

        while (true){
            server.receive(receive);

            byte[] recvByte = Arrays.copyOfRange(receive.getData(), 0, receive.getLength());

            System.out.println("收到: " + new String(recvByte));
        }

    }
}
