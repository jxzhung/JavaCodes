package com.jzhung.util;

import org.junit.Test;

/**
 * Created by Jzhung on 2017/6/23.
 */
public class FileEncryptUtilTest {
    @Test
    public void testEncrypt(){
        FileEncryptUtil.encrypt("D:\\1.ppt");
    }

    @Test
    public void testBin(){
        int a = 12;// 1100
        System.out.println(Integer.toBinaryString(a << 1)); // 11000 = 24
        System.out.println(Integer.toBinaryString(a >> 1)); // 110 = 6
        System.out.println(Integer.toBinaryString(~a) + "=" + ~a); // 11111111111111111111111111110011 = -13
        System.out.println(Integer.toBinaryString(1|a)); // 1101 = 13
    }
}
