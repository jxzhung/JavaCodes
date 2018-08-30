package com.jzhung.demo.tcpip;

/*
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.EthernetPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

*/
/**
 * 抓取本机HTTP数据
 * Created by Jzhung on 2017/1/6.
 *//*

public class JpcapMain {
    private static final int TYPE_REQUEST_GET = 1;
    private static final int TYPE_REQUEST_PSOT = 2;
    private static final int TYPE_REQUEST_RESPONSE = 3;
    private static final int TYPE_UNKNOW = 4;

    public byte[] getByte;
    public byte[] postByte;
    public byte[] respByte;


    public static void main(String[] args) {
        new JpcapMain().startCapture();
    }

    public void startCapture() {

        try {
            getByte = "GET".getBytes("iso-8859-1");//利用单字节的iso-8859-1转码以便与抓取的数据做对比
            postByte = "POST".getBytes("iso-8859-1");
            respByte = "HTTP".getBytes("iso-8859-1");

            NetworkInterface[] devices = JpcapCaptor.getDeviceList();
            for (NetworkInterface device : devices) {
                System.out.println(device.description);
            }

            NetworkInterface targetInterface = devices[2];//本地连接的网卡
            JpcapCaptor captor = JpcapCaptor.openDevice(targetInterface, 65535, false, 20); //
            //captor.setFilter("host 122.141.231.18", true);//filter packets from ip
            //Receiver receiver = new Receiver(captor.getPacket());//just get one packet
            captor.loopPacket(-1, new Receiver()); //Receiver
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("fail to dispaly the data of network interface--" + exception);
        }

    }


    */
/**
     * 自定义包处理器
     */

    /*class Receiver implements PacketReceiver {
        public void receivePacket(Packet packet) {
            if (packet instanceof TCPPacket) {// 仅捕获TCP包
                //EthernetPacket epack = (EthernetPacket) packet.datalink;
                //System.out.print("Mac source: " + epack.getSourceAddress() + " dest: " + epack.getDestinationAddress());

                TCPPacket tcpPack = (TCPPacket) packet;
                //System.out.println(" IP from src " + tcpPack.src_ip + ":" + tcpPack.src_port + " to " + tcpPack.dst_ip + ":" + tcpPack.dst_port);
                //System.out.println(tcpPack.toString());
                //System.out.println(new String(new String(tcpPack.data)));
                int dateType = getDataType(tcpPack.data);
                switch (dateType) {
                    case TYPE_REQUEST_GET:
                        System.out.println(new String(new String(tcpPack.data)));
                        break;
                    case TYPE_REQUEST_PSOT:
                        System.out.println(new String(new String(tcpPack.data)));
                        break;
                    case TYPE_REQUEST_RESPONSE:
                        System.out.println(new String(new String(tcpPack.data)));
                        break;
                    default:
                        break;
                }
            }
        }
    }*/

/**
     * 对比数据头判断数据包类型
     *
     * @param bytes
     * @return
     *//*

    public int getDataType(byte[] bytes) {
        int type = 0;//未比对
        for (int i = 0; i < getByte.length; i++) {
            if (getByte[i] != bytes[i]) {
                type = -1;
                continue;
            }
        }
        if (type == 0) {
            return TYPE_REQUEST_GET;
        }

        type = 0;
        for (int i = 0; i < respByte.length; i++) {
            //System.out.println((char) respByte[i] + " --> " + (char) bytes[i]);
            if (respByte[i] != bytes[i]) {
                type = -1;
                continue;
            }
            //System.out.println("type=" + type);
        }
        if (type == 0) {
            //System.out.println("---------------------------响应--------------------");
            return TYPE_REQUEST_RESPONSE;
        }

        type = 0;
        for (int i = 0; i < postByte.length; i++) {
            if (postByte[i] != bytes[i]) {
                type = -1;
                continue;
            }
        }
        if (type == 0) {
            return TYPE_REQUEST_PSOT;
        }

        return TYPE_UNKNOW;
    }
}
*/
