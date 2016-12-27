package com.t600.http;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.*;

/**
 * Created by Jzhung on 2016/12/12.
 */
public class JsonTest {
    @Test
    public void PostSendUserCenter() throws IOException {
        String url = "http://192.168.1.9:8891/json/userRecoder/addUserrecoder.action";
        String result = Request.Post(url).bodyForm(
                Form.form()
                        .add("student.studentId", "70035")
                        .add("data", readFileContent("e:\\s.txt"))
                        .build()).execute().returnContent().asString();
        System.out.println(result);
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return
     */
    public static String readFileContent(String filePath){
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            return null;
        }
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line;
            while ((line = br.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
