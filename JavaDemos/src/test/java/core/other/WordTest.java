package core.other;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ����� on 2017/8/7.
 */
public class WordTest {
    String ua = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3226.400 QQBrowser/9.6.11681.400";
    String refer = "http://xh.5156edu.com/";

    @Test
    public void getWord() throws IOException {
        String keyword = "好";
        String postUrl = "http://xh.5156edu.com/index.php";

        Connection connect = Jsoup.connect(postUrl);
        connect.timeout(10000);
        connect.header("User-Agent", ua);
        connect.header("Referer", refer);

        connect.data("f_key", URLEncoder.encode(keyword, "gb2312"));
        connect.data("f_type", "zi");
        connect.data("SearchString.x", "31");
        connect.data("SearchString.y", "18");
        /*Connection.Response resp = connect.post();
        System.out.println(resp.statusCode());
        System.out.println(resp.header("Location"));*/


        Document post = connect.post();
        System.out.println(post.text());
    }

    @Test
    public void testWordConvert() throws UnsupportedEncodingException {
        String word = "好";
        //%BA%DC
        System.out.println("%BA%DC");
        System.out.println(URLEncoder.encode(word, "utf-8"));
        System.out.println(URLEncoder.encode(word, "gb2312"));

    }

    public String Utf8URLencode(String text) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            }else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                }catch (Exception ex) {
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }
}
