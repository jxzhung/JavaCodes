package core.socket.LANBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Jzhung on 2016/11/22.
 */
public class Client {
    public static void main(String[] args) {
        startReceiveMsg();
    }

    private static void startReceiveMsg() {
        int port = 9999;//开启监听的端口
        DatagramSocket ds = null;
        DatagramPacket dp = null;
        byte[] buf = new byte[1024];//存储发来的消息
        StringBuffer sbuf = new StringBuffer();
        try {
            //绑定端口的
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("监听广播端口打开：");
            while (true) {
                ds.receive(dp);
                int i;
                for (i = 0; i < 1024; i++) {
                    if (buf[i] == 0) {
                        break;
                    }
                    sbuf.append((char) buf[i]);
                }
                System.out.println("收到广播消息：" + sbuf.toString());
                sbuf.delete(0, sbuf.length());
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
