package core.socket.LANBroadcast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2016/11/22.
 */
public class Server {
    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {
        // 广播的实现 :由客户端发出广播，服务器端接收
        String host = "255.255.255.255";//广播地址
        int port = 9999;//广播的目的端口
        try {
            InetAddress adds = InetAddress.getByName(host);
            DatagramSocket ds = new DatagramSocket();

            NetService aservice = new NetService();
            aservice.setUuid("TSERVICE-BOARD-SERVER");
            aservice.setIp("192.168.1.216");
            aservice.setPort(8889);
            aservice.setDesc("教学服务");

            Gson gson = new Gson();

            while (true) {
                String message = gson.toJson(aservice);
                byte[] sengData = message.getBytes("UTF-8");
                DatagramPacket dp = new DatagramPacket(sengData,
                        sengData.length, adds, port);
                ds.send(dp);
                System.out.println("SEND TO " + dp.getSocketAddress() + " DATA:" + message);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
