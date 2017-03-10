package com.jzhung.server.report.service;

import com.jzhung.server.report.bean.AppReport;

/**
 * 报告服务
 * Created by Jzhung on 2017/3/3.
 */
public interface IReportService {
    /**
     * 保存上报信息
     *
     * @param appReport
     */
    void saveReport(AppReport appReport);
}
