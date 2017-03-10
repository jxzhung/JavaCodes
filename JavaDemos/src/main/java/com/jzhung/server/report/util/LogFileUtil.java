package com.jzhung.server.report.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jzhung on 2017/3/3.
 */
public class LogFileUtil {
    private static File logDir;
    private static File logFile;
    private static FileWriter writer = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static File getLogFile() {
        String file = sdf.format(new Date());
        logFile = new File(logDir, file);
        return logFile;
    }

    public static void writeAppReport(String log) {
        synchronized (LogFileUtil.class) {
            FileWriter writer = getFileWriter();
            try {
                writer.write(log);
                writer.write("\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: 2017/3/3 下一步按日期获取多个日志文件
    private static FileWriter getFileWriter() {
        if (writer != null) {
            return writer;
        }

        try {
            writer = new FileWriter(getLogFile(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

}
