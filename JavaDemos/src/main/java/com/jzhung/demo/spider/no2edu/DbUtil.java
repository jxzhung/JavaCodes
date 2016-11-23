package com.jzhung.demo.spider.no2edu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2016/11/22.
 */
public class DbUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/kecheng?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    public static final String name = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";

    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static List<BookVersion> getAllBookVersions(){
        Connection conn = getConn();
        String sql = "SELECT gl. NAME, gl.gLevelId, s.`name`, s.subjectId, bs.`name`, bs.bSeriesId, bsv.`name`," +
                " bsv.ed FROM `subject` AS s, grade_level AS gl, book_series AS bs, subject_book_series AS sbs," +
                " book_series_version AS bsv WHERE sbs.bSeriesId = bs.bSeriesId AND sbs.subjectId = s.subjectId" +
                " AND sbs.gLevelId = gl.gLevelId AND sbs.bSeriesId = bsv.bSeriesId;";
        List<BookVersion> bookVersionList = new ArrayList<BookVersion>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                BookVersion bv = new BookVersion();
                bv.setGradeLevelName(rs.getString(1));
                bv.setGradeLevelId(rs.getInt(2));
                bv.setSubjectName(rs.getString(3));
                bv.setSubjectId(rs.getInt(4));
                bv.setSeriesName(rs.getString(5));
                bv.setSeriesId(rs.getInt(6));
                bv.setBookName(rs.getString(7));
                bv.setBookId(rs.getInt(8));
                bookVersionList.add(bv);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookVersionList;
    }

}
