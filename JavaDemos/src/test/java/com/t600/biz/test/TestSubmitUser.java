package com.t600.biz.test;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSubmitUser {
    private List<String> ids = new ArrayList<String>();
    private static String content = "";

    public static void main(String[] args) throws Exception {
        TestSubmitUser main = new TestSubmitUser();
        //main.selectStudentTag();
        main.getContent();
        for (int i = 0; i < 10; i++) {
            main.getAllId();
        }
        main.startThread();
//        main.submit("70035", content);
    }

    private void selectStudentTag() throws Exception {
        FileWriter fw = new FileWriter("C:\\Users\\Jzhung\\Desktop\\63523029.txt", true);
        String tagPath = "C:\\Users\\Jzhung\\Desktop\\newTag1.txt";
        File file = new File(tagPath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if(tempString.contains("63523029")){
                    fw.write(tempString + "\n");
                    fw.flush();
                }
            }
            fw.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void SelectTag() throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\Jzhung\\Desktop\\newTag1.txt", true);
        String tagPath = "C:\\Users\\Jzhung\\Desktop\\catalina.txt";
        File file = new File(tagPath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if(tempString.contains("http://192.168.8.199:80/json/userRecoder/addUserrecoder.action")){
                    fw.write(tempString + "\n");
                    fw.flush();
                }
            }
            fw.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void startThread() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50000);
        for (int j = 0; j < 500 * 10; j++) {
            final int finalJ = j;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(finalJ);
                    submit(ids.get(finalJ), content);
                }
            });
        }
    }

    private void getContent() {
        String contentPath = "C:\\Users\\Jzhung\\Desktop\\s.txt";
        File file = new File(contentPath);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void getAllId() {
        String idPath = "C:\\Users\\Jzhung\\Desktop\\id1.txt";
        File file = new File(idPath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                ids.add(tempString);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void submit(final String id, final String content) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(Charset.forName("utf-8"));
            builder.addTextBody("student.studentId", id);
            builder.addTextBody("data", content);
            Request.Post("http://192.168.1.143:8080/json/userRecoder/addUserrecoder.action")
                    .body(builder.build())
                    .execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
