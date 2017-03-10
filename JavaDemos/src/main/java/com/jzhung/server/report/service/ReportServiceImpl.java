package com.jzhung.server.report.service;

import com.google.gson.Gson;
import com.jzhung.server.report.bean.AppReport;
import com.jzhung.server.report.util.LogFileUtil;

/**
 * Created by Jzhung on 2017/3/3.
 */
public class ReportServiceImpl implements IReportService {
    private Gson gson = new Gson();

    public void saveReport(AppReport appReport) {
        String fullAppReport = gson.toJson(appReport);
        LogFileUtil.writeAppReport(fullAppReport);
    }
}
