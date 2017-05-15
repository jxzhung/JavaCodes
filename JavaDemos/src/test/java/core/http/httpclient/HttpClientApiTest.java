package core.http.httpclient;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

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
        for (int i = 1; i < 170; i++) {
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

            if (listphones == null) {
                continue;
            }
            //System.out.println(pageindex);
            for (int i1 = 0; i1 < listphones.size(); i1++) {
                System.out.println(listphones.get(i1).getPhoneNumber());
            }
        }

    }

    @Test
    public void testUpload() throws IOException {
        //String postURL = "http://192.168.1.163:8080/resource/save";
        String postURL = "http://127.0.0.1:8080/resource/save2";
        long time = System.currentTimeMillis();
        byte[] fileData = ("测试数据" + time).getBytes();//文件不要太长
        String name = new String(("测试文件" + time + ".txt").getBytes("iso8859-1"));

        //创建表单
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("utf-8"));
        builder.addTextBody("name", name);
        builder.addTextBody("resType", "3");
        builder.addTextBody("resYear", "2017");
        builder.addTextBody("fileType", "plain/text");
        builder.addTextBody("from", "disk");
        builder.addTextBody("gradeLevelId", "1");
        builder.addTextBody("subjectId", "1");
        builder.addTextBody("bookSeriesId", "1");
        builder.addTextBody("bookId", "1");
        builder.addTextBody("bookPartCode", "_1_2");
        builder.addBinaryBody("resFile", fileData, ContentType.TEXT_PLAIN, name);
        String json = Request.Post(postURL)
                .addHeader("Cookie", "teacherLogin=94; _ga=GA1.1.1861785523.1489739241; UM_distinctid=15bd23cacaa440-015c390f19a52c-5c4f231c-13c680-15bd23cacab810; CNZZDATA1253226906=2134290492-1493876123-%7C1493881525; JSESSIONID=2A03B9CE5C6D699ED2B1096907D5E9CD")
                .connectTimeout(20000)
                .socketTimeout(20000)
                .body(builder.build())
                .execute().returnContent().asString();
        System.out.println(json);
    }

    @Test
    public void getPhone() throws IOException {
        List<String> urlList = new ArrayList<String>();
        urlList.add("http://www.wlmqsbz.com/");
        urlList.add("http://www.xiangyu.com/");
        urlList.add("http://www.bjytzhx.com/main");
        urlList.add("http://gz.gaokao789.com/tem1.asp?sid=4&id=1398");
        urlList.add("http://gz.gaokao789.com/tem1.asp?sid=4&id=1439");

        String telRegex = "\\d{7}";
        String addressRegex = "";
        String postcodeRegex = "";
        String wechatRegex = "";
        String schoolMasterRegex = "";

        Pattern telPtn = Pattern.compile(telRegex);
        Pattern addressPtn = Pattern.compile(addressRegex);
        Pattern postcodePtn = Pattern.compile(postcodeRegex);
        Pattern wechatPtn = Pattern.compile(wechatRegex);
        Pattern schoolMasterPtn = Pattern.compile(schoolMasterRegex);


        for (String url : urlList) {
            Document doc = Jsoup.connect(url).get();
            //System.out.println(doc.text());
            // 第一遍过滤
            StringTokenizer st = new StringTokenizer(doc.text(), " ");


            School school = new School();
            while (st.hasMoreTokens()){
                String text = st.nextToken().trim();
                //System.out.println(text);
                if(text.contains("地址")){
                    //System.out.println("地址---");
                    school.address = text + st.nextToken();
                }
                if(text.contains("电话") && telPtn.matcher(text).find()){
                    //System.out.println("电话---" + text);
                    school.tel = text + st.nextToken().trim();
                }

            }
            System.out.println("学校信息：" + school.toString());

        }

    }

    class School{
        String tel;
        String address;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "School{" +
                    "tel='" + tel + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
