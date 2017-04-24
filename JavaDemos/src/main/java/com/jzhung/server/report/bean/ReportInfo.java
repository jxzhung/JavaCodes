package com.jzhung.server.report.bean;

/**
 * Created by Jzhung on 2017/3/3.
 */
public class ReportInfo {
    private String fromIp;//报告来源IP
    private String reportTime;

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}
