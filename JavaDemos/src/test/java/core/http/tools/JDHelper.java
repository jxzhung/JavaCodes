package core.http.tools;

import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2017/5/15.
 */
public class JDHelper {

    @Test
    public void getTicket() throws IOException, InterruptedException {
        String url = "http://coupon.jd.com/ilink/couponSendFront/send_index.action?key=05dd51715d76498ca55b8ca31d6d770c&roleId=6466647&to=sale.jd.com/act/kwjukadirxpa.html&";
        String cookie = "ser-key=507fba47-56f8-4532-b358-456f67d1e4cb; ipLoc-djd=1-2800-2850-0.138645467; ipLocation=%u5317%u4eac; cn=15; dmpjs=dmp-d501561d14dd7710bee2c94c52966aee0d77bc2; _jrda=2; wlfstk_smdl=5azx95iqn7uwmtnocdtj891k364vr9tw; TrackID=1DLckpMybJd7RQpKbiIPeml9PuKFxBr9aNXFdOJy5jIb6DqHm_ytqOvrYwouWVjT4hvAa-L2raXgUQZpxUHwu-vdSr53uB9gxABA4cRk7y4Y; pinId=QMtru9Ec4R4gM0-V1DsWEw; pin=zza280110900; unick=%E5%A2%A8%E5%9F%8E888; _tp=V8tsO6WjRUnZpADGWZpZIQ%3D%3D; _pst=zza280110900; ceshi3.com=203; __jdv=122270672|dmp|dmp_109|cpc|dmp_109_516249_d501561d14dd7710bee2c94c52966aee0d77bc2_1494817139|1494818328033; thor=DE57AD9A4C59D1B7FC1F1E9629620C117C503E027BABC99F2E17B3C12674F500A2F5CD049B8482B32989C7B006DC5AF6BFB33B1EB0DFB61AB2DD098139C68236306681883A719122FD0FE457E2B942F450F0AF1767BF6967BA09939421D95B02E49C76D6811A195C1B63B84C2A08E14CB7EBB6A337A8FFE32E4CDFA4A4DD129E36A608302C562E6B8991661312A24F57; __jda=122270672.1815344299.1475048738.1494826649.1494828768.35; __jdb=122270672.2.1815344299|35.1494828768; __jdc=122270672; 3AB9D23F7A4B3C9B=Y7EF5ZTOHFJDAGO5KROKBA2WZXQWYWW2DPJG5KRZEEBBKBBE2R2MUCLCWY675EAFKGUMM3RI5PD6MFH5BBJC4RKXRI; __jdu=1815344299";
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
        String refer = "http://sale.jd.com/act/kwjukadirxpa.html";


        int hour = 14;//开始小时
        int minute = 57;//开始分钟
        int jiange = 3;//间隔几分钟

        long checkTime = 2000;
        long trySleep = 400;

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 4, 15, hour, minute, 02);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(System.currentTimeMillis()));
        long time;

        while (cal.getTimeInMillis() > System.currentTimeMillis()) {
            System.out.println(".");
            TimeUnit.MILLISECONDS.sleep(checkTime);
        }
        System.out.println("时间到了");

        while (Math.abs((time = System.currentTimeMillis()) - cal.getTimeInMillis()) < jiange * 60000) {
            System.out.println(sdf.format(time));
            String content = Request.Get(url).addHeader("Cookie", cookie).addHeader("Referer", refer)
                    .userAgent(ua)
                    .addHeader("Upgrade-Insecure-Requests", "1").execute().returnContent().asString();
            Document doc = Jsoup.parse(content);
            Elements ctxt02 = doc.getElementsByClass("ctxt02");
            if(ctxt02 != null && ctxt02.size() > 0){
                Element first = ctxt02.first();
                String text = first.text();
                System.out.println(text);
                if(text.contains("请15:00再来")){
                    System.out.println("重试。。。");
                    TimeUnit.MILLISECONDS.sleep(trySleep);
                }else if(text.contains("活动太火爆")){
                    System.out.println("被限制，等待2秒。。。");
                    TimeUnit.MILLISECONDS.sleep(2000);
                }else {
                    System.out.println("出结果了" + text);
                    break;
                }
            }else {
                System.out.println("没提示了！出结果了？");
            }
            //System.out.println(content);
        }

        System.out.println("运行完毕...");
    }
}
