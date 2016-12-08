package database;

import org.junit.Test;

/**
 * Created by jzhung on 2016/11/20.
 */
public class MysqlTest {
    @Test
    public void genClassSQL(){
        /*for (int i = 47; i <= 65; i++) {
            System.out.printf("INSERT INTO `classs` VALUES (%d, '班级%d', '实验班', 2016, 7, NULL);\n", i, i);
        } */
        for (int i = 18; i <= 26; i++) {
            System.out.printf("INSERT INTO `grade` VALUES (%d, '%s');\n", i, "年级" + i);
        }
    }
}
