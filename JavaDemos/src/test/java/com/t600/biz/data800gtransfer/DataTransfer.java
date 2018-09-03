package com.t600.biz.data800gtransfer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2017/10/19.
 */
public class DataTransfer {
    private List<Teacher> resTeacherList = new ArrayList<Teacher>();
    private List<Res> middleResList = new ArrayList<Res>();


    public static void main(String[] args) {
        new DataTransfer().start();
    }

    private void start() {
        addResTeacher();

        start:
        //遍历每个老师获取所有资源
        for (Teacher teacher : resTeacherList) {
            List<Res> resList = DataTransferDbUtil.getTeacherAllRes(teacher.teacherId);
            //查询出相同科目的新老师 teacherId与本老师不相等的
            List<Teacher> newTeacherList = DataTransferDbUtil.getNewTeachers(teacher.teacherId, teacher.lessonId);
            System.out.println("resList:" + resList.size());
            System.out.println("newTeacherList:" + newTeacherList.size());
            for (Teacher curTeahcer : newTeacherList) {
                try {
                    addResToEachTeacher(resList, curTeahcer);
                } catch (SQLException e) {
                    e.printStackTrace();
                    break start;
                }
            }
        }

        DataTransferDbUtil.closeConnection();
    }

    private void addResToEachTeacher(List<Res> resList, Teacher curTeahcer) throws SQLException {
        //构建树形结构
        buildResTree(curTeahcer);

        //插入资源获取 最大ID
        /*int maxId = DataTransferDbUtil.getMaxResId();
        for (Res res : resList) {
            //更新主键 父ID 和 teacherId
            res.setResId(res.getResId() + maxId);
            if(res.getParentId() != null && res.getParentId() != 0){
                res.setParentId(res.getParentId() + maxId);
            }else {
                res.setParentId(null);
            }
            res.setTeacherId(curTeahcer.teacherId);
            DataTransferDbUtil.insertRes(res);
        }*/
    }

    private void buildResTree(Teacher curTeahcer) {

    }


    private void addResTeacher() {
        Teacher t1 = new Teacher();
        t1.teacherId = 183;
        t1.lessonId = 2;
        resTeacherList.add(t1);

        Teacher t2 = new Teacher();
        t2.teacherId = 183;
        t2.lessonId = 1;
        resTeacherList.add(t2);

        Teacher t3 = new Teacher();
        t3.teacherId = 183;
        t3.lessonId = 3;
        resTeacherList.add(t3);

        Teacher t4 = new Teacher();
        t4.teacherId = 183;
        t4.lessonId = 4;
        resTeacherList.add(t4);

        Teacher t5 = new Teacher();
        t5.teacherId = 183;
        t5.lessonId = 5;
        resTeacherList.add(t5);

        Teacher t6 = new Teacher();
        t6.teacherId = 183;
        t6.lessonId = 6;
        resTeacherList.add(t6);

        Teacher t7 = new Teacher();
        t7.teacherId = 183;
        t7.lessonId = 7;
        resTeacherList.add(t7);

        Teacher t8 = new Teacher();
        t8.teacherId = 183;
        t8.lessonId = 8;
        resTeacherList.add(t8);

        Teacher t9 = new Teacher();
        t9.teacherId = 183;
        t9.lessonId = 9;
        resTeacherList.add(t9);
    }
}
