package com.t600.biz.data800gtransfer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2017/10/19.
 */
public class DataTransferDbUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/ols_guangao_test?useUnicode=true&characterEncoding=UTF-8";
    //private static String url = "jdbc:mysql://t600.com.cn:13307/ols_guangao?useUnicode=true&characterEncoding=UTF-8";
    private static String username = "root";
    //private static String password = "TQJzxc!@#$!!!";
    private static String password = "123456";
    private static Connection connection;


    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driver); //classLoader,加载对应驱动
                connection = (Connection) DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Res> getTeacherAllRes(int teacherId) {
        Statement stmt = null;
        List<Res> resList = new ArrayList<Res>();
        try {
            stmt = getConnection().createStatement();
            String sql = "SELECT * from course_resource WHERE teacherId=" + teacherId + " order by resId ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Res res = new Res();
                res.setResId(rs.getInt("resId"));
                res.setResName(rs.getString("name"));
                res.setResType(rs.getString("resType"));
                res.setFileType(rs.getString("fileType"));
                res.setExaminationUUID(rs.getString("examination_UUID"));
                res.setParentId(rs.getInt("parentId"));

                res.setIsPublish(rs.getInt("isPublish"));
                res.setIsVisible(rs.getInt("isVisible"));
                res.setTeacherId(rs.getInt("teacherId"));
                res.setCreateTime(rs.getTimestamp("createTime"));
                res.setUpdateTime(rs.getTimestamp("updateTime"));
                res.setWeight(rs.getInt("weight"));

                res.setFileSize(rs.getDouble("size"));

                res.setIsScore(rs.getInt("isScore"));
                res.setIsShare(rs.getInt("isShare"));

                res.setIsExam(rs.getInt("isExam"));
                res.setR_index(rs.getInt("r_index"));
                res.setRecordTime(rs.getString("recordTime"));
                res.setFilee(rs.getInt("fileId"));

                resList.add(res);
                System.out.println(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public static int getMaxResId() {
        //SELECT MAX(resId) FROM course_resource
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sql = "SELECT MAX(resId) FROM course_resource";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Teacher> getNewTeachers(int teacherId, int lessonId) {
        Statement stmt = null;
        List<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            stmt = getConnection().createStatement();
            String sql = "SELECT teacherId, lessonId from teacher WHERE teacherId != " + teacherId + " and lessonId = " + lessonId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int tId = rs.getInt("teacherId");
                int lId = rs.getInt("lessonId");
                Teacher teacher = new Teacher();
                teacher.teacherId = tId;
                teacher.lessonId = lId;
                teacherList.add(teacher);
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    public static void insertRes(Res res) throws SQLException {
        System.out.println("保存：" + res);
        PreparedStatement pstmt = null;
        String sql = "INSERT into course_resource VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = getConnection().prepareStatement(sql);
        pstmt.setInt(1, res.getResId());

        pstmt.setString(2, res.getResName());
        pstmt.setString(3, res.getResType());
        pstmt.setString(4, res.getFileType());
        pstmt.setString(5, res.getFilePath());
        pstmt.setString(6, res.getExaminationUUID());

        if(res.getParentId() == null || res.getParentId() == 0){
            pstmt.setString(7, null);
        }else {
            pstmt.setInt(7, res.getParentId());
        }



        pstmt.setInt(8, res.getIsPublish());
        pstmt.setInt(9, res.getIsVisible());
        pstmt.setInt(10, res.getTeacherId());

        pstmt.setTimestamp(11, res.getCreateTime());
        pstmt.setTimestamp(12, res.getUpdateTime());

        pstmt.setInt(13, res.getWeight());
        pstmt.setDouble(14, res.getFileSize());

        pstmt.setInt(15, res.getIsScore());
        pstmt.setInt(16, res.getIsShare());
        pstmt.setInt(17, res.getIsExam());
        pstmt.setInt(18, res.getR_index());

        pstmt.setString(19, res.getRecordTime());
        if(res.getFilee() == 0){
            pstmt.setString(20, null);
        }else {
            pstmt.setInt(20, res.getFilee());
        }

        pstmt.executeUpdate();


    }
}
