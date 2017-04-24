package core.db.jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.Random;

/**
 * Created by Jzhung on 2017/1/13.
 */
public class MysqlTest {
    private final String DRIVE_NAME = "com.mysql.jdbc.Driver";
    private String uri = "jdbc:mysql://127.0.0.1:3306/ols_gy_zy?useUnicode=true&characterEncoding=utf-8";
    //private String uri = "jdbc:mysql://192.168.1.9:3307/ols_gy_zy?useUnicode=true&characterEncoding=utf-8";
    private String user = "root";
    private String password = "123456";
    private Random rdm = new Random();
    private String[] choice = {"A", "B", "C", "D"};
    private String[] mulChoice = {"AB", "AC", "AD", "BD", "BC", "BD", "CD", "ABC", "ABD", "ABCD", "A", "B", "C", "D"};
    private String[] judge = {"对", "错"};

    @Test
    public void insert() {
        Connection conn = getConnection();
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 4; i++) {
                String sql = "INSERT INTO `exercise_answer_detial` VALUES (NULL , ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                for (int j = 0; j < 100; j++) {
                    pstmt.setInt(1, getRandom(10000));
                    pstmt.setInt(2, getRandom(10010001));
                    pstmt.setInt(3, getRandom(20));
                    int qtype = getRandom(5);
                    pstmt.setInt(4, qtype);
                    pstmt.setString(5, getRandomAnswer(qtype));
                    pstmt.setString(6, getRandomAnswer(3));
                    pstmt.setInt(7, getRandom(5));
                    pstmt.setInt(8, getRandom(5));
                    pstmt.setString(9, "UUID" + i);
                    pstmt.addBatch();
                    //System.out.println("插入一个学生答案" + j);
                }
                pstmt.executeBatch();
                System.out.println(i);
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("共耗时：" + time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getRandomAnswer(int type) {
        switch (type){
            //单选
            case 1:
                return choice[rdm.nextInt(choice.length)];
            case 2:
                return mulChoice[rdm.nextInt(mulChoice.length)];
            case 3:
                return judge[rdm.nextInt(judge.length)];
            case 4:
                return "答案" + type;
            default:
                return "答案";
        }
    }

    public int getRandom(int max) {
        return rdm.nextInt(max) + 1;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVE_NAME);
            conn = DriverManager.getConnection(uri, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
