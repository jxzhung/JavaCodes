package com.t600.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2017/7/17.
 */
public class QiNiuSDK {
    private UploadManager uploadManager;
    private String upToken;
    private List<File> uploadFileList;

    @Test
    public void upload() throws IOException {
        String accessKey = "yourkey";
        String secretKey = "yourkey2";
        String bucket = "node1";
        String localFilePath = "E:\\word.txt";
        String key = "txt/" + new File(localFilePath).getName();

        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        uploadManager = new UploadManager(cfg);
        upToken = auth.uploadToken(bucket);

        uploadFileList = new ArrayList<File>();
        String baseDir = "E:\\名师视频";
        loadFiles(new File(baseDir));
        System.out.println("token: " + upToken);

        for (File file : uploadFileList) {
            localFilePath = file.getAbsolutePath();
            String newFile = localFilePath.replace("E:\\名师视频", "E:\\sp");
            File newF = new File(newFile);
            newF.getParentFile().mkdirs();
            newF.createNewFile();
        }

        /*for (File file : uploadFileList) {
            localFilePath = file.getAbsolutePath();
            key = file.getAbsolutePath().replace("E:\\", "").replace("\\", "/");
            System.out.println(localFilePath + " --> " + key);
            upload(localFilePath, key);
        }*/
    }

    public void upload(String localFilePath, String key){
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public void loadFiles(File dir){
        File[] files = dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                loadFiles(file);
            }else {
                uploadFileList.add(file);
            }
        }

    }
}
