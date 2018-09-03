package com.jzhung.util;

import java.io.*;

/**
 * FFmpeg工具类，用于音视频转换
 * @author jzhung.com
 * Created by Jzhung on 2017/9/9.
 */
public class FFmpegUtil {

    /**
     * amr转mp3，在Win和Linux上都要上要设置好ffpmeg,
     * WIN下放置ffmpeg.exe到c:\ffmpeg\ffmpeg.exe
     * LINUX下放到/ffmpeg/ffmpeg 并给调用用户可执行权限，也可chmod 777 ffmpeg给予全部权限
     *
     * @param amr
     * @param mp3
     * @throws IOException
     */
    public static void arm2mp3(String amr, String mp3) throws IOException {
        String ffmpeg = null;
        if (getOS().trim().toLowerCase().startsWith("win")) {
            ffmpeg = "c:\\ffmpeg\\ffmpeg.exe";
        } else {
            ffmpeg = "/ffmpeg/ffmpeg";
        }
        //先删除已经存在的文件
        new File(mp3).delete();
        String cmd = String.format("%s -i %s %s", ffmpeg, amr, mp3);
        FFmpegUtil.execute(cmd);
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

    private static String getDefaultEncoding() {
        if (getOS().trim().toLowerCase().startsWith("win")) {
            return "GBK";
        } else {
            return "UTF-8";
        }
    }

    private static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

}
