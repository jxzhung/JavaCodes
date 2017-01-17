package core.hash;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import static sun.security.pkcs11.wrapper.Functions.toHexString;

/**
 * Created by Jzhung on 2017/1/16.
 */
public class HashTest {
    public static void main(String[] args) throws Exception {
        String file = "D:\\固件工具\\固件\\update现代派原厂固件.img";
        long begin = System.currentTimeMillis();
        String hash = getHash(file, "SHA1");
        long time = System.currentTimeMillis() - begin;
        System.out.println(hash + " 耗时:" + time);
    }

    private static String getHash(String fileName, String hashType)
            throws Exception {
        InputStream fis = new FileInputStream(fileName);
        byte buffer[] = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        for (int numRead = 0; (numRead = fis.read(buffer)) > 0; ) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }
}
