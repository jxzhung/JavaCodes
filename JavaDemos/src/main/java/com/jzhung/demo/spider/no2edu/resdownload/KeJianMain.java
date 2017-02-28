package com.jzhung.demo.spider.no2edu.resdownload;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 抓取全国各版本教材课程目录信息
 *
 * @author jzhung
 */
public class KeJianMain {
    private List<Unit> unitList = new ArrayList<Unit>();
    //单元列表
    private List<ResFile> resList = new ArrayList<ResFile>();//文件列表
    private String baseDir;
    private int index = 437;//位置索引
    private String logFile;

    public static void main(String[] args) {
        //getSubjects();
        //getVersions();
        KeJianMain task = new KeJianMain();

        String url = "http://s.dearedu.com/list.php?g=1&su=3&e=77&ed=116";
        task.baseDir = "Z:\\公司资源（添加修改请联系郭工、李工）\\教学资源存档\\";

       /* long start = System.currentTimeMillis();
        task.downbookRes(url);
        task.downUnitRes();
        long end = System.currentTimeMillis() - start;
        System.out.println("获取下载地址耗时：" + end / 1000);*/

        task.logFile = "Z:\\公司资源（添加修改请联系郭工、李工）\\教学资源存档\\log_高中-英语-外研版-必修2-.txt";
        task.loadDownloadLinkFromFile();
        // task.downResFile();
        task.downResFile1Thread();
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
                        downloader.downFile(resFile.url, resFile.destFile);
                        System.out.println("下载完毕");
                    }
                }
            });
        }
    }

    private void downResFile1Thread() {
        final Downloader downloader = new Downloader();
        final int total = resList.size();
        for (int i = index; i < total; i++) {
            ResFile resFile = resList.get(i);
            System.out.println("下载文件:" + (i + 1) + "/" + total + " " + resFile.url);
            downloader.downFile(resFile.url, resFile.destFile);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    private void downUnitRes() {
        System.out.println("获取单元资料开始");

        try {
            FileWriter fileWriter = new FileWriter(new File(logFile));

            int totalUnit = unitList.size();
            for (int i = 0; i < unitList.size(); i++) {
                Unit unit = unitList.get(i);
                System.out.println("处理单元 " + unit.url + " " + (i + 1) + "/" + totalUnit);
                // 载入第一页
                Document doc = getDoc(unit.url);
                Element pageDiv = doc.select(".p_page").first();
                //System.out.println(pageDiv.html());

                //获取总页数
                Element lastPageDiv = pageDiv.getAllElements().last();
                System.out.println(lastPageDiv.attr("abs:href"));
                unit.tatalPage = 10;

                //分页爬取
                int totalPage = unit.tatalPage;
                for (int page = 1; page <= unit.tatalPage; page++) {
                    System.out.println("处理单元 " + (i + 1) + "/" + totalUnit + " 页面 " + page + "/" + totalPage);
                    Document pageDoc = getDoc(unit.url + "&p=" + page);
                    Element listDiv = pageDoc.select(".lb_grey").first();

                    Elements resItems = listDiv.select(".lb_aload");
                    //遍历处理每个资源div
                    for (int itemIndex = 0; itemIndex < resItems.size(); itemIndex++) {
                        Element itemDiv = resItems.get(itemIndex);
                        Element nameDiv = itemDiv.select("a").first();
                        String name = nameDiv.attr("title");
                        System.out.println(name);

                        Element downlink = itemDiv.select(".lb_clkto").first().select("a").first();
                        String downUrl = downlink.attr("abs:href");
                        if (downUrl == null || downUrl.equals("")) {
                            System.out.println("无下载链接");
                            continue;
                        }

                        ResFile resFile = new ResFile();
                        resFile.url = downUrl;
                        resFile.destFile = unit.getLocalDir() + "\\" + name;

                        //写记录
                        fileWriter.write(downUrl + "|" + resFile.destFile);
                        fileWriter.write("\n");
                        fileWriter.flush();

                        resList.add(resFile);
                        System.out.println("添加下载链接：" + downUrl);
                    }
                }
            }
            fileWriter.close();
            System.out.println("获取单元资料结束 资源：" + resList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单元
     */
    private void downbookRes(String url) {
        System.out.println("处理：" + url);
        System.out.println("获取单元开始");

        Document doc = getDoc(url);
        //创建教材目录
        Element positionDiv = doc.select(".address").first();
        Elements posADivs = positionDiv.select("a");
        StringBuilder dirBuilder = new StringBuilder();
        for (int i = 0; i < posADivs.size(); i++) {
            Element posItemDiv = posADivs.get(i);
            dirBuilder.append(posItemDiv.text());
            dirBuilder.append("\\");
        }
        String dir = dirBuilder.toString();
        logFile = baseDir + "\\log_" + dir.replace("\\", "-") + ".txt";
        baseDir = baseDir + dirBuilder.toString();
        System.out.println("教材目录：" + baseDir);

        Element em = doc.getElementById("lTREEMenuDEMO");
        //获取所有tag dd
        Elements dds = em.getElementsByTag("dd");
        //System.out.println(em.html());

        for (int i = 0; i < dds.size(); i++) {
            Element dd = dds.get(i);
            Elements links = dd.select("a[href]");

            String partName = "未知章节";
            for (int j = 0; j < links.size(); j++) {
                Element link = links.get(j);
                String nodeName = link.attr("title");
                //第一个节点是单元名
                if (j == 0) {
                    partName = nodeName.trim();
                    System.out.println(nodeName);
                    new File(baseDir, partName).mkdirs();
                } else {
                    /*if (genUnitId <= 0) {
                        throw new RuntimeException("获取不到新增单元的ID");
                    }*/
                    System.out.println("---- " + nodeName);
                    String unitDir = baseDir + "\\" + partName + "\\" + nodeName.trim();
                    new File(unitDir).mkdirs();
                    Unit unit = new Unit();
                    unit.url = link.attr("abs:href");
                    unit.localDir = unitDir;
                    unitList.add(unit);
                }

            }
        }
        System.out.println("单元处理完毕 单元:" + unitList.size());
    }

    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(10000).userAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
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

