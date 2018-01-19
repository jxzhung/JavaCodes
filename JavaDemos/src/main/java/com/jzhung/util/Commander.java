package com.jzhung.util;

/**
 * Created by Jzhung on 2018/1/19.
 */
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令行辅助工具
 * @author jzhung
 */
public class Commander {

    @Test
    public void test() {
        List<String> cmds = new ArrayList<String>();
        cmds.add("ping 127.0.0.1");
        cmds.add("ipconfig /all");
        cmds.add("adb devices");
        /*cmds.add("adb shell ls /system");
        cmds.add("adb shell pm list packages");*/

        try {
            for (int i = 0; i < cmds.size(); i++) {
                execute(cmds.get(i));
                System.out.println("-----------------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();
        SequenceInputStream sequenceIs = new SequenceInputStream(inStream, errStream);
        BufferedInputStream bufStream = new BufferedInputStream(sequenceIs);
        Reader reader = new InputStreamReader(bufStream, getDefaultEncoding());
        BufferedReader bufReader = new BufferedReader(reader);
        String line;
        while ((line = bufReader.readLine()) != null) {
            System.out.println(line);
        }
        inStream.close();
        errStream.close();
        process.destroy();
    }

    public static String getDefaultEncoding() {
        if (getOS().trim().toLowerCase().startsWith("win")) {
            return "GBK";
        } else {
            return "UTF-8";
        }
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        System.out.println(os);
        return os;
    }
}