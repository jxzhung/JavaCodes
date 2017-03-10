package com.jzhung.server.report.bean;

/**
 * Created by Jzhung on 2017/3/3.
 */
public class AppInfo {
    private String appName; //应用名称
    private String packageName; //应用包名
    private String appVersion; //应用版本
    private int appVersionCode; //应用版本代码
    private String appSignature; //应用签名

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppSignature() {
        return appSignature;
    }

    public void setAppSignature(String appSignature) {
        this.appSignature = appSignature;
    }
}
