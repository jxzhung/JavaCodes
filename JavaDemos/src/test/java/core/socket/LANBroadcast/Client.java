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
                String data = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
                System.out.println("收到" + dp.getSocketAddress() + "的广播消息：" + data);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
