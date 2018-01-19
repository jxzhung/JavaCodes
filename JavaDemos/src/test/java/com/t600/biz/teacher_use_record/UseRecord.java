package com.t600.biz.teacher_use_record;

import java.sql.Timestamp;

/**
 * Created by Jzhung on 2017/7/26.
 */
public class UseRecord {
    private Integer useRecordId;
    private String schoolName;
    private Integer teacherLocalId;
    private String teacherName;
    private Integer recordType;
    private String recordDesception;
    private Timestamp occurTime;
    private Timestamp createTime;

    public Integer getUseRecordId() {
        return useRecordId;
    }

    public void setUseRecordId(Integer useRecordId) {
        this.useRecordId = useRecordId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getTeacherLocalId() {
        return teacherLocalId;
    }

    public void setTeacherLocalId(Integer teacherLocalId) {
        this.teacherLocalId = teacherLocalId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getRecordDesception() {
        return recordDesception;
    }

    public void setRecordDesception(String recordDesception) {
        this.recordDesception = recordDesception;
    }

    public Timestamp getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Timestamp occurTime) {
        this.occurTime = occurTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
