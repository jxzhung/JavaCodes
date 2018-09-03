package com.jzhung.demo.tcpip.netsniffer;

import org.junit.Test;

/**
 * Created by Jzhung on 2018/6/20.
 */
public class HexUtilTests {

    @Test
    public void hexStringToBytes() {
        String hex = "450000343c6d400040067af8c0a80108c0a80106f65f0050cbeb4dcb000000008002faf0e0590000020405b40103030801010402".toUpperCase();
        byte[] bytes = HexStringToBinary(hex);
        String hexBinary =BinaryToHexString(bytes) ;
        System.out.println(hex);
        System.out.println(hexBinary);
        System.out.println(hexBinary.equals(hex));
    }

    /**
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    private static String hexStr = "0123456789ABCDEF"; //全局

    public static String BinaryToHexString(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位  
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位  
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;
        }
        return result;
    }

    /**
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static byte[] HexStringToBinary(String hexString) {
        //hexString的长度对2取整，作为bytes的长度  
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位  
        byte low = 0;//字节低四位  

        for (int i = 0; i < len; i++) {
            //右移四位得到高位  
            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);//高地位做或运算
        }
        return bytes;
    }
}
