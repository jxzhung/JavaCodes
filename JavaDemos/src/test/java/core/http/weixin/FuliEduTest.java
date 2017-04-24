package core.http.weixin;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/3/22.
 */
public class FuliEduTest {

    @Test
    public void getHtml() throws IOException {
        String url = "http://weixin.qq.com/q/02_yE6xwa1fG1100000034";
        String url2 = "http://www.fuulea.com/tk/p-3907/";
        String cookie = "sessionid=g1w2sd7192tia4f1qrveon40duh3ca0a";
        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Mobile/14D27 MicroMessenger/6.5.5 NetType/WIFI Language/zh_CN";
        String content = Request.Get(url2)

                .addHeader("Cookie", cookie)
                .execute()
                .returnContent()
                .asString();
        System.out.println(content);
    }

    @Test
    public void createQR() throws IOException {
        String token = "";
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
        String body = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"http://www.fuulea.com/tk/1000/\"}}}";
        String content = Request.Post(url).bodyString(body, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        System.out.println(content);

    }
}
