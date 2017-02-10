package com.jzhung.demo.spider.no2edu.resdownload;

import com.jzhung.demo.core.util.DocumentUtil;
import org.apache.hadoop.util.hash.Hash;
import org.apache.http.client.fluent.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jzhung on 2017/2/8.
 */
public class WeiKeMain {
    private List<Unit> unitList = new ArrayList<Unit>();
    private List<ResFile> resList = new ArrayList<ResFile>();//文件列表
    private BASE64Decoder decoder = new BASE64Decoder();
    private String baseUrl;
    private String baseDir;
    private int index = 0;//位置索引
    private String logFile;

    public static void main(String[] args) {
        WeiKeMain task = new WeiKeMain();
        task.baseUrl = "http://v.dearedu.com/list_g.php?xueduan=3&xueke=6&zhuti=2";
        task.baseDir = "E:\\资源库\\微课\\高中\\生物\\知识点讲解\\";
        task.logFile = "E:\\资源库\\log-微课-高中-生物-知识点讲解-基因工程.txt";
        //task.loadUnit();
        task.loadUnitWithIndex(21);//仅下载指定单元
        task.loadUnitVideo();
        task.printResFile();

        task.loadDownloadLinkFromFile();
        task.downResFile();
    }

    private void printResFile() {
        System.out.println("=========================================");
        for (ResFile resFile : resList) {
            System.out.println(resFile.url + "|" + resFile.destFile);
        }
        System.out.println("=========================================");
    }

    private void loadUnitVideo() {
        try {
            FileWriter fileWriter = new FileWriter(new File(logFile));
            int total = unitList.size();
            for (int i = 0; i < unitList.size(); i++) {
                Unit unit = unitList.get(i);
                System.out.println("处理小节" + (i + 1) + "/" + total + " " + unit.url);
                Document doc = DocumentUtil.getDoc(unit.url);


                Elements videoItemDiv = doc.select("div.ondemand");
                //System.out.println("视频节点数量" + videoItemDiv.size());
                // FIXME: 2017/2/8 先下载首页的
                for (int i1 = 0; i1 < videoItemDiv.size(); i1++) {
                    Element vitem = videoItemDiv.get(i1);
                    Element link = vitem.select("a").first();
                    String url = link.attr("href");
                    //System.out.println("视频地址:" + url);
                    //组装视频地址 http://v.dearedu.com/show.php?zhuti=1&id=16
                    String zhutiId = unit.url.substring(unit.url.lastIndexOf("=") + 1);
                    String id = url.substring(url.lastIndexOf("=") + 1);
                    String videoUrl = "http://v.dearedu.com/show.php?zhuti=" + zhutiId + "&id=" + id;
                    System.out.println("目标视频地址:" + videoUrl);

                    //保存的文件名
                    Element font = vitem.getElementsByTag("font").first();
                    String fileName = font.text().trim() + ".mp4";
                    System.out.println("文件名:" + fileName);
                    String saveDir = unit.localDir + "\\" + fileName;

                    //获取视频地址
                    Document videoDoc = DocumentUtil.getDoc(videoUrl);
                    Elements scripts = videoDoc.getElementsByTag("script");
                    String videoRealUrl = null;
                    for (int i2 = 0; i2 < scripts.size(); i2++) {
                        Element script = scripts.get(i2);
                        //System.out.println("-------------------------------------");
                        String scriptText = script.html();
                        //System.out.println(scriptText);
                        if (scriptText.startsWith("var video_url")) {
                            //System.out.println("发现 video_url");
                            videoRealUrl = parseVideoUrl(script.html());
                            if (videoRealUrl != null) {
                                continue;
                            }
                        }

                    }
                    if (videoRealUrl == null) {
                        System.out.println("无视频地址跳过-----------------" + videoUrl);
                        continue;
                    }
                    videoRealUrl = getFromBase64(videoRealUrl);
                    System.out.println("视频地址：" + videoRealUrl);
                    //System.out.println("存储位置：" + videoRealUrl);

                    ResFile resFile = new ResFile();
                    resFile.url = videoRealUrl;
                    resFile.destFile = saveDir;
                    resList.add(resFile);

                    //写记录
                    fileWriter.write(videoRealUrl + "|" + resFile.destFile);
                    fileWriter.write("\n");
                    fileWriter.flush();
                }

            }
            fileWriter.close();
            System.out.println("获取单元资料结束 资源：" + resList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String parseVideoUrl(String html) {
        String mark = "var video_url = guolv('";
        int index = html.indexOf(mark);
        if (index != -1) {
            int endIndex = html.indexOf("');");
            return html.substring(index + mark.length(), endIndex);
        }
        return null;
    }

    public void loadUnit() {
        Document doc = DocumentUtil.getDoc(baseUrl);
        Element em = doc.getElementById("lTREEMenuDEMO");
        //获取所有tag dd
        Elements dds = em.child(0).children();
        //System.out.println(em.html());
        for (int i = 0; i < dds.size(); i++) {
            Element dd = dds.get(i);
            doLoadUnit(dd);
        }
        System.out.println("--------------------");
    }

    public void loadUnitWithIndex(int index) {
        Document doc = DocumentUtil.getDoc(baseUrl);
        Element em = doc.getElementById("lTREEMenuDEMO");
        //获取所有tag dd
        Elements dds = em.child(0).children();
        //System.out.println(em.html());
        Element dd = dds.get(index);
        doLoadUnit(dd);
        System.out.println("--------------------");
    }

    private void doLoadUnit(Element dd) {
        Elements links = dd.select("a");
//            System.out.println(i);

        Map<String, String> nameMap = new HashMap<String, String>();
        String partName = "未知章节";
        for (int j = 0; j < links.size(); j++) {
            //System.out.println(i + "-" + j);
            Element link = links.get(j);
            String nodeName = link.attr("title").trim();


            //单元名
            Element linkParent = link.parent();
            String parentClass = linkParent.attr("class");
            //System.out.println(nodeName + " " + parentClass);
            String index = parentClass.substring(parentClass.length() - 1);
            nameMap.put(index, nodeName);
            //System.out.println(index + ":" + nodeName);

            String url = link.attr("abs:href");
            //System.out.println(url);
            if (url != null && !url.equals("")) {
                String level = parentClass.substring(parentClass.length() - 1);
                int levelInt = Integer.parseInt(level);
                StringBuilder dirBuilder = new StringBuilder();

                for (int i1 = 1; i1 <= levelInt; i1++) {
                    dirBuilder.append(nameMap.get(i1 + ""));
                    dirBuilder.append("\\");
                }

                System.out.println(dirBuilder + " url:" + url);

                Unit unit = new Unit();
                unit.url = link.attr("abs:href");
                unit.localDir = baseDir + "\\" + dirBuilder.toString();
                unitList.add(unit);
                new File(unit.localDir).mkdirs();
            }
        }

    }

    public void loadUnit2() {
        Document doc = DocumentUtil.getDoc(baseUrl);
        Element em = doc.getElementById("lTREEMenuDEMO");
        //获取所有tag dd
        Elements dds = em.getElementsByTag("dd");
        //System.out.println(em.html());

        for (int i = 0; i < dds.size(); i++) {
            Element dd = dds.get(i);
            Elements links = dd.select("a");

            String partName = "未知章节";
            for (int j = 0; j < links.size(); j++) {
                Element link = links.get(j);
                String nodeName = link.attr("title");
                //第一个节点是单元名
                if (j == 0) {
                    partName = nodeName.trim();
                    System.out.println(nodeName);
                    String zDir = baseDir + partName;
                    //System.out.println("章节" + zDir);
                    new File(zDir).mkdirs();
                } else {
                    /*if (genUnitId <= 0) {
                        throw new RuntimeException("获取不到新增单元的ID");
                    }*/
                    String unitDir = baseDir + "\\" + partName + "\\" + nodeName.trim();
                    System.out.println("---- " + nodeName);
                    //System.out.println("---- " + unitDir);
                    new File(unitDir).mkdirs();
                    Unit unit = new Unit();
                    unit.url = link.attr("abs:href");
                    unit.localDir = unitDir;
                    unitList.add(unit);
                }
            }
        }
    }

    private void downResFile() {
        final Downloader downloader = new Downloader();
        final int total = resList.size();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        final Random rdm = new Random();

        for (int i = 0; i < 6; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(rdm.nextInt(1500) + 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (index < total) {
                        ResFile resFile = takeResFile();
                        if (resFile == null) {
                            System.out.println("获取不到资源下载地址了！结束");
                            return;
                        }
                        downloader.downFileRirectly(resFile.url, resFile.destFile);
                    }
                }
            });
        }
    }

    private synchronized ResFile takeResFile() {
        if (resList.size() == 0 || index > resList.size() - 1) {
            return null;
        }
        ResFile resFile = resList.get(index);
        System.out.println(Thread.currentThread().getName() + "下载文件:" + (index + 1) + "/" + resList.size() + " " + resFile.url);
        index++;
        return resFile;
    }

    // 解密
    public String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {

            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void loadDownloadLinkFromFile() {
        resList.clear();
        File logFileF = new File(logFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(logFileF));
            String line;
            while ((line = br.readLine()) != null) {
                int split = line.indexOf("|");
                ResFile resFile = new ResFile();
                resFile.url = line.substring(0, split);
                resFile.destFile = line.substring(split + 1, line.length());
                resList.add(resFile);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("共载入资源：" + resList.size());
    }

}
