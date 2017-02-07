package core;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Jzhung on 2017/2/3.
 */
public class BinaryTest {

    @Test
    public void start() throws UnsupportedEncodingException {
        String redirectUrl2 = "http://down.dearedu.com/d161/2016100503/201610050151.ppt";
        System.out.println(redirectUrl2);
        int index1 = redirectUrl2.lastIndexOf("/");
        String fileName = redirectUrl2.substring(index1 + 1);
        System.out.println(fileName);
        String newName = URLEncoder.encode(fileName, "utf-8");

        redirectUrl2 = redirectUrl2.substring(0, index1 + 1) + newName;
        System.out.println(redirectUrl2);
    }

    @Test
    public void binartTest(){
        int a = 1;
        int a2 = 2;
        int a3 = 88;
        System.out.printf("%10s\n", Integer.toBinaryString(a));
        System.out.printf("%10s\n", Integer.toBinaryString(a2));
        System.out.printf("%10s\n", Integer.toBinaryString(a3));
    }

    @Test
    public void fileEncrypt() throws IOException {
        String filePath = "d:\\1.pptx";
        File file = new File(filePath);
        if(file.length() >= 50) {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
          /*  raf.seek(8);
            int x = raf.read();
            System.out.println(~x);
            System.out.println(Integer.toBinaryString(x));*/

            for (int i = 0; i < 50; i++) {
                raf.seek(i);

                int data = raf.read();
                data = ~data;
                raf.seek(i);

                raf.write(data);
                System.out.println(data);
            }
          

            raf.close();
        }
    }
}
