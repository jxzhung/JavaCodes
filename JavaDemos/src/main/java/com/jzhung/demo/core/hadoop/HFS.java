package com.jzhung.demo.core.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by 郭信谊 on 2017/1/12.
 * Edit by 钟家兴 2017-1-13 12:51:18
 */
public class HFS {
    private static Configuration conf;
    private static FileSystem fs;

    public static void main(String args[]) throws IOException, URISyntaxException, InterruptedException {
        //pullFile("hdfs://192.168.1.210:9000/jdk/jdk-8u111-linux-x64.rpm", "d:/jdk.rpm");
        HFS.initFs();

        //testGetInputStream();

        System.out.println("断网后回车：");
        HFS.pushFile("D:\\1.pptx", "/file/1.pptx");


        //mkdir("/file");

        for (int i = 0; i < 0; i++) {
//            HFS.pushFile("E:\\cn.wps.moffice_eng_166-v9.9.3.apk", "/jzhung/cn.wps.moffice_eng_166-v9.9.3_" + System.currentTimeMillis() + ".apk");
//            HFS.pushFile("E:\\ATQJwendaoweike.apk", "/jzhung/ATQJwendaoweike_" + System.currentTimeMillis() + ".apk");
//            HFS.pushFile("E:\\com.example.launcher3-v1.10.9-c64.apk", "/jzhung/com.example.launcher3-v1.10.9-c64_" + System.currentTimeMillis() + ".apk");
            HFS.pushFile("D:\\ceshi\\hadoop\\jzhung.png", "/file/icon_" + i + "_" + System.currentTimeMillis() + ".png");
            System.out.println("发送：");
        }
    }

    private static void testGetInputStream() {
        String file = "/jzhung2/icon_0_1484288327344.png";
        try {
            InputStream in = getFileInputStream(file);
            byte[] buffer = new byte[1024];
            int read = 0;
            FileOutputStream fout = new FileOutputStream("D:\\1.png");
            while ((read = in.read(buffer)) != -1){
                fout.write(buffer, 0, read);
            }
            fout.flush();
            fout.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件输入流
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static InputStream getFileInputStream(String filePath) throws IOException {
        Path path = new Path(filePath);
        FSDataInputStream open = fs.open(path, 4096);
        return open.getWrappedStream();
    }

    public static void initFs() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("init");
        long start = System.currentTimeMillis();
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        conf = new Configuration();
        conf.addResource(new Path("D:/ceshi/hadoop/core-site.xml"));
        conf.addResource(new Path("D:/ceshi/hadoop/hdfs-site.xml"));
        long end = System.currentTimeMillis() - start;
        System.out.println("init complete time:" + end);
        fs = FileSystem.get(new URI("hdfs://192.168.1.210:9000"), conf, "admin");
        //fs = FileSystem.get(new URI("hdfs://192.168.1.178:9000"), conf, "root");
    }

    //创建新文件
    public static void createFile(String dst, byte[] contents) throws IOException {
        //FileSystem fs = FileSystem.get(conf);
        Path dstPath = new Path(dst); //目标路径
        //打开一个输出流
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write(contents);
        outputStream.close();
        //fs.close();
        System.out.println("文件创建成功！");
    }

    //上传本地文件
    public static void pushFile(String src, String dst) {
        System.out.println("pushFile start");
        try {
            Path srcPath = new Path(src); //原路径
            Path dstPath = new Path(dst); //目标路径
            long beginTime = System.currentTimeMillis();
            fs.copyFromLocalFile(srcPath, dstPath);
            long endTime = System.currentTimeMillis();

            //打印文件路径
            System.out.println("pushFile complete");
            System.out.println("------------list files------------" + "\n");
            FileStatus[] fileStatus = fs.listStatus(dstPath);
            long fileSize = 0;
            for (FileStatus file : fileStatus) {
                System.out.println(file);
                fileSize = file.getLen();
            }
            //fs.close();
            System.out.println("time = " + String.valueOf(endTime - beginTime) + "ms, speed = " + String.valueOf(fileSize / (endTime - beginTime)) + "kb/s");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    //文件重命名
    public static void rename(String oldName, String newName) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path oldPath = new Path(oldName);
        Path newPath = new Path(newName);
        boolean isok = fs.rename(oldPath, newPath);
        if (isok) {
            System.out.println("rename ok!");
        } else {
            System.out.println("rename failure");
        }
        fs.close();
    }

    //删除文件
    public static void delete(String filePath) throws IOException {
        //FileSystem fs = FileSystem.get(conf);
        Path path = new Path(filePath);
        boolean isok = fs.deleteOnExit(path);
        if (isok) {
            System.out.println("delete ok!");
        } else {
            System.out.println("delete failure");
        }
        //fs.close();
    }

    //创建目录
    public static void mkdir(String path) throws IOException, URISyntaxException, InterruptedException {
        //FileSystem fs = FileSystem.get(new URI("hdfs://192.168.1.210:9000"), conf, "admin");
        //FileSystem fs = FileSystem.get(getConfig());
        Path srcPath = new Path(path);

        if (fs.exists(srcPath)) {
            System.out.println("already exists!");
            return;
        }

        boolean isok = fs.mkdirs(srcPath);
        if (isok) {
            System.out.println("create dir ok!");
        } else {
            System.out.println("create dir failure");
        }
        //fs.close();
    }

    //下载文件
    public static void pullFile(String filePath, String localPath) {
        try {
            System.out.println("pullFile start");
            //FileSystem fs = FileSystem.get(conf);
            Path srcPath = new Path(filePath);
            FileStatus[] fileStatus = fs.listStatus(srcPath);
            long fileSize = 0;
            for (FileStatus file : fileStatus) {
                System.out.println("file: " + file);
                fileSize = file.getLen();
            }
            Path desPath = new Path(localPath);
            long beginTime = System.currentTimeMillis();
            fs.copyToLocalFile(false, srcPath, desPath, true);
            long endTime = System.currentTimeMillis();
            System.out.println("pullFile complete, time = " + String.valueOf(endTime - beginTime) + "ms, speed = " + String.valueOf(fileSize / (endTime - beginTime)) + "kb/s");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
