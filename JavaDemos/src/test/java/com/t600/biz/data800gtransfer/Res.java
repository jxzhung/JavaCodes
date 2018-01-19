package com.t600.biz.data800gtransfer;

import java.sql.Timestamp;

/**
 * Created by Jzhung on 2017/10/19.
 */
public class Res {
    private Integer resId;
    private String resName;
    private String resType;
    private String fileType;
    private Integer filee;
    private String filePath;
    private String examinationUUID;
    private Integer parentId;
    private int isPublish; //0 默认   未发布, 1 发布
    private int isVisible; //0 默认   不可见, 1 可见
    private Integer isExam;
    private Integer teacherId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int weight;
    private Double fileSize;
    private int isShare;
    private int isScore;
    private Integer r_index;
    private String recordTime;//教师版pad端的录音时长 从端接收

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFilee() {
        return filee;
    }

    public void setFilee(Integer filee) {
        this.filee = filee;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getExaminationUUID() {
        return examinationUUID;
    }

    public void setExaminationUUID(String examinationUUID) {
        this.examinationUUID = examinationUUID;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(int isPublish) {
        this.isPublish = isPublish;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getIsExam() {
        return isExam;
    }

    public void setIsExam(Integer isExam) {
        this.isExam = isExam;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public Integer getR_index() {
        return r_index;
    }

    public void setR_index(Integer r_index) {
        this.r_index = r_index;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getIsScore() {
        return isScore;
    }

    public void setIsScore(int isScore) {
        this.isScore = isScore;
    }

    @Override
    public String toString() {
        return "Res{" +
                "resId=" + resId +
                ", resName='" + resName + '\'' +
                ", resType='" + resType + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filee=" + filee +
                ", filePath='" + filePath + '\'' +
                ", examinationUUID='" + examinationUUID + '\'' +
                ", parentId=" + parentId +
                ", isPublish=" + isPublish +
                ", isVisible=" + isVisible +
                ", isExam=" + isExam +
                ", teacherId=" + teacherId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", weight=" + weight +
                ", fileSize=" + fileSize +
                ", isShare=" + isShare +
                ", isScore=" + isScore +
                ", r_index=" + r_index +
                ", recordTime='" + recordTime + '\'' +
                '}';
    }
}
