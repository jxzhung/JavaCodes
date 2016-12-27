package core;

import org.junit.Test;

import java.io.*;

/**
 * Created by Jzhung on 2016/12/23.
 */
public class FileEncrypt {
    public static void main(String[] args) throws IOException {
        File dir = new File("E:\\flv");
        File saveDir = new File("E:\\flv-解密");
        saveDir.mkdirs();
        File[] files = dir.listFiles();

        for (File file : files) {
            File dstFile = new File(saveDir, file.getName());
            decrypt(file, dstFile);
        }
    }

    @Test
    public static void decrypt(File srcFile, File dstFile) throws IOException {
        System.out.println(1);

        FileOutputStream fout = new FileOutputStream(dstFile);
        BufferedOutputStream bufferOut = new BufferedOutputStream(fout);

        FileInputStream fin = new FileInputStream(srcFile);
        byte[] buf = new byte[1024];
        int read = 0;
        while ((read = fin.read(buf)) != -1){
            for (int i = 0; i < buf.length; i++) {
                byte b = buf[i];
                //System.out.println(b);
                buf[i] = (byte)(b ^ 32);
                //System.out.println(b);
            }
            bufferOut.write(buf);
        }
        bufferOut.flush();
        bufferOut.close();
        fin.close();
    }

    private void print(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
    }
}
