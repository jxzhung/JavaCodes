package core.http.httpclient;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;

/**
 * HttpClient Fluent Api测试
 */
public class HttpClientFluentApiTest {
    String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 115Browser/7.2.2";

    @Test
    public void get() throws IOException {
        String url = "http://www.csdn.net";
        String content = Request.Get(url).execute().returnContent().asString();
        System.out.println(content);
    }

    public void post() {

    }
}
