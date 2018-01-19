package com.t600.map;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2017/7/17.
 */
public class BaiduMap {

    @Test
    public void testHtmlUnit() throws IOException, InterruptedException {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        final HtmlPage page = webClient.getPage("http://map.baidu.com/");
        TimeUnit.SECONDS.sleep(5);
        HtmlTextInput input = (HtmlTextInput) page.getElementById("sole-input");
        input.setValueAttribute("天津 艺术班");
        HtmlButton sbtn = (HtmlButton) page.getElementById("search-button");
        sbtn.click();
        TimeUnit.SECONDS.sleep(5);

        System.out.println(page.asText());
        webClient.close();
    }

}
