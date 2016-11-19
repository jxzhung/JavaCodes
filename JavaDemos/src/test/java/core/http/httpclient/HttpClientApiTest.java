package core.http.httpclient;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 测试 HttpClient API
 * Created by jzhung on 2016/11/19.
 */
public class HttpClientApiTest {

    /**
     * Apache HttpClient的Fluent API POST提交文件示例
     * @throws IOException
     */
    @Test
    public void testPost() throws IOException {

        String fileName = "test.txt";
        byte[] bytes = "this is a test file".getBytes();

        //创建表单
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("utf-8"));
        builder.addTextBody("user", "jzhung");
        builder.addBinaryBody("file", bytes, ContentType.MULTIPART_FORM_DATA, fileName);

        String url = "http://test.com/upload.do";
        Request.Post(url)
                .connectTimeout(20000)
                .socketTimeout(20000)
                .body(builder.build())
                .execute().returnContent().asString();
    }
}
