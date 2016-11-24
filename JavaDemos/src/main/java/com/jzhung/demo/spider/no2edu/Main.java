package com.jzhung.demo.spider.no2edu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * 抓取全国各版本教材课程目录信息
 *
 * @author jzhung
 */
public class Main {
    public static void main(String[] args) {
        //getSubjects();
        //getVersions();
        try {
            importUnit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入单元数据
     */
    private static void importUnit() throws SQLException {
        List<BookVersion> bookVersionList = DbUtil.getAllBookVersions();
        System.out.println(bookVersionList.size());

        Connection conn = DbUtil.getConn();

        int total = bookVersionList.size();

        for (int k = 335; k < total; k++) {
            BookVersion bv = bookVersionList.get(k);
            String url = "http://s.dearedu.com/list.php?g=" + bv.getGradeLevelId() + "&su=" + bv.getSubjectId() + "&e=" + bv.getSeriesId() + "&ed=" + bv.getBookId();
            System.out.println("处理：" + k + "/" + total + " " + url);
            Element em = getDoc(url).getElementById("lTREEMenuDEMO");
            //获取所有tag dd
            Elements dds = em.getElementsByTag("dd");
            //System.out.println(em.html());
            for (int i = 0; i < dds.size(); i++) {
                Element dd = dds.get(i);
                Elements links = dd.select("a[href]");

                int genUnitId = 0;
                for (int j = 0; j < links.size(); j++) {
                    Element link = links.get(j);
                    String nodeName = link.attr("title");
                    //第一个节点是单元名
                    if (j == 0) {
                        //System.out.println(nodeName);
                        //String unitSql = "insert into book_unit (name, bsvId, bsId, subjectId, gradeId) values (?,?,?,?,?)";
                        String unitSql = "insert into book_unit values(NULL, ?, ?, ?, ?, ?)";
                        //sqlBuilder.append(unitSql);
                        //System.out.println(unitSql);
                        PreparedStatement ps1 = conn.prepareStatement(unitSql, Statement.RETURN_GENERATED_KEYS);
                        ps1.setString(1, nodeName);
                        ps1.setInt(2, bv.getBookId());
                        ps1.setInt(3, bv.getSeriesId());
                        ps1.setInt(4, bv.getSubjectId());
                        ps1.setInt(5, bv.getGradeLevelId());
                        ps1.executeUpdate();
                        ResultSet rs = ps1.getGeneratedKeys();
                        while (rs.next()) {
                            genUnitId = rs.getInt(1);
                        }
                        if (genUnitId > 0) {
                            System.out.println(nodeName + " 新建成功 ID:" + genUnitId);
                        }
                    } else {
                        if (genUnitId <= 0) {
                            throw new RuntimeException("获取不到新增单元的ID");
                        }
                        //System.out.println("---- " + nodeName);
                        String unitSql = "insert into book_unit_lesson values(NULL, ?, ?)";
                        //System.out.println(unitSql);
                        PreparedStatement ps2 = conn.prepareStatement(unitSql);
                        ps2.setString(1, nodeName);
                        ps2.setInt(2, genUnitId);
                        boolean result = ps2.execute();
                        System.out.println("---- " + nodeName + (!result ? " SUCCESS" : " FAILED++++++++++++++++++++++++++"));
                    }

                }
            }
        }
        conn.close();
    }


    /**
     * 生成课程版本和关联关系SQL语句
     */
    private static void getVersions() {
        Element em = getDoc(Const.URL_VERSION_LINKS).select(".hide666").first();
        Elements links = em.select("a[href]");
        Set<String> bookVersionKeySet = new HashSet<String>();
        //所有科目页面链接
        for (Element link : links) {
            String subject = link.text();
            String slink = link.attr("abs:href");
            //System.out.println(subject + slink);
            //处理一个科目获取所有版本
            Element verionNode = getDoc(slink).select(".hide555").first();
            Elements versionlinks = verionNode.select("a[href]");

            for (Element vlink : versionlinks) {
                String vurl = vlink.attr("abs:href");
                //System.out.println(vurl);
                String vname = vlink.text();
                String su = vurl.substring(vurl.indexOf("su=") + 3, vurl.indexOf("&e")); //科目
                String g = vurl.substring(vurl.indexOf("g=") + 2, vurl.indexOf("&su")); //年级
                String e = vurl.substring(vurl.indexOf("e=") + 2, vurl.length()); //版本
                // 1 导入版本
                if (!bookVersionKeySet.contains(e)) {
                    bookVersionKeySet.add(e);
                    //System.out.printf("INSERT INTO `book_version` VALUES (%s, '%s');\n", e, vname);

                    // 3 导入版别
                    Element bvbNode = getDoc(vurl).select(".hide444").first();
                    Elements bvblinks = bvbNode.select("a[href]");
                    for (Element bvblink : bvblinks) {
                        String bvburl = bvblink.attr("abs:href");
                        String ed = bvburl.substring(bvburl.indexOf("ed=") + 3, bvburl.length());
                        String bvbname = bvblink.text();
                        //System.out.println(vname + " 书 " + bvbname);
                        //System.out.printf("INSERT INTO `book_version_book` VALUES (null, %s, %s, '%s');\n", ed, e, bvbname);
                    }
                }

//                // 2 导入关联关系 只能执行一遍
//                System.out.printf("INSERT INTO `subject_book_version` VALUES (%s, %s, %s);\n", g, su, e);
            }
        }
    }

    private static void getSubjects() {
        try {
            Document doc = Jsoup.connect(Const.URL_LESSON).userAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0").get();
            Element e = doc.select(".hide666").first();
            String rawText = e.text();
            StringTokenizer st = new StringTokenizer(rawText, " ");
            String lesson;

            List<String> gzList = new ArrayList<String>();
            List<String> czList = new ArrayList<String>();
            List<String> xxList = new ArrayList<String>();

            List tagert = null;
            while (st.hasMoreTokens()) {
                lesson = st.nextToken();
                if (lesson.equals("高中")) {
                    tagert = gzList;
                } else if (lesson.equals("初中")) {
                    tagert = czList;
                } else if (lesson.equals("小学")) {
                    tagert = xxList;
                } else {
                    tagert.add(lesson);
                }
                //System.out.println(st.nextToken());
            }

            System.out.println("高中：");
            for (String g : gzList) {
                System.out.println(g);
            }
            System.out.println("初中：");
            for (String g : czList) {
                System.out.println(g);
            }
            System.out.println("小学：");
            for (String g : xxList) {
                System.out.println(g);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }


}
