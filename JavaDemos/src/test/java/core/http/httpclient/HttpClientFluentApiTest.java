package core.http.httpclient;

import com.jzhung.demo.core.MainDemo;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;

/**
 * HttpClient Fluent Api测试
 */
public class HttpClientFluentApiTest {
    @Test
    public void get() throws IOException {
        String url = "http://www.csdn.net";
        String content = Request.Get(url).execute().returnContent().asString();
        System.out.println(content);
    }

    public void post(){

    }
}
