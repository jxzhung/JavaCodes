package core.http.httpclient;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 测试 HttpClient API
 * Created by jzhung on 2016/11/19.
 */
public class HttpClientApiTest {

    /**
     * Apache HttpClient的Fluent API POST提交文件示例
     *
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

    @Test
    public void testGetPhoneNum() throws IOException {
        Gson gson = new Gson();
        for (int i = 1; i < 26; i++) {
            String url = "http://www.189.cn/sales/basedata/combonumber.do?" +
                    "systemType=1&salesProdId=000000003D655A34435B3DB7E053AA1410AC8503&shopId=10001" +
                    "&comboDetailsId=&channelId=&pageindex=" + i + "&pagesize=8&areacode=8110100" +
                    "&minpay=&prettypattern=&contnumber=&cacheId=&maxPage=44&numbertype=0" +
                    "&phoneNumMinExpense=&subPhoneNumMinExpense=&phoneNumPrestoreExpense=" +
                    "&mall_price=0&fourFlag=0&minExpenseCloud=&inflag=0&lastFlag=0" +
                    "&headNumber=&sortby=1&type=&numberLevel=&innumber=&procod=" +
                    "&areacod=&maxpay=&_=1481614966863";
            String content = Request.Get(url).execute().returnContent().asString();
            PhoneResponse response = gson.fromJson(content, PhoneResponse.class);
            int pageindex = response.getDataObject().getPageindex();
            List<PhoneResponse.DataObjectEntity.ListphonesEntity> listphones = response.getDataObject().getListphones();

            if(listphones == null){
                continue;
            }
            System.out.println(pageindex);
            for (int i1 = 0; i1 < listphones.size(); i1++) {
                System.out.println(listphones.get(i1).getPhoneNumber());
            }
        }

    }


}
