package core.http;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jzhung on 2017/10/13.
 */
public class HttpTest {
    @Test
    public void testUrl(){
        String str = "http://127.0.0.1:123/get?name=zhang";
        try {
            URL url = new URL(str);
            System.out.println(url.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
