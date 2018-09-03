package core;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/3/8.
 */
public class JsonTest {

    @Test
    public void json(){
        try {
            String json = Request.Get("http://aliyun.t600.com.cn:8080/config/schools.json").execute().returnContent().asString();
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
    }
}
