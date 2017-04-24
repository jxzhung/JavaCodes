package core.encode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by Jzhung on 2017/1/11.
 */
public class EncodeTest {

    @Test
    public void encodeTest() throws UnsupportedEncodingException {
        String str = "你好";
        byte[] gbkByte = str.getBytes("gbk");
        String result1 = new String(gbkByte, "iso-8859-1");
        String result2 = new String(gbkByte, "utf-8");
        String result3 = new String(gbkByte, "gbk");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);


        System.out.println("---------------");
        byte[] utfByte = str.getBytes("utf-8");
        String result21 = new String(utfByte, "iso-8859-1");
        String result22 = new String(utfByte, "utf-8");
        String result23 = new String(utfByte, "gbk");
        System.out.println(result21);
        System.out.println(result22);
        System.out.println(result23);


        System.out.println("---------------");
        byte[] isoByte = str.getBytes("iso-8859-1");
        String result31 = new String(isoByte, "iso-8859-1");
        String result32 = new String(isoByte, "utf-8");
        String result33 = new String(isoByte, "gbk");
        System.out.println(result31);
        System.out.println(result32);
        System.out.println(result33);
    }
}
