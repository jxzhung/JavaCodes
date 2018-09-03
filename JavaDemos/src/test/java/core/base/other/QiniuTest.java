package core.base.other;

import com.qiniu.util.Auth;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Jzhung on 2017/11/14.
 */
public class QiniuTest {
    private char[] chars = new char[26+26+10+2];
    private String randomKey;
    private Random rdm = new Random();
    StringBuilder builder = new StringBuilder();

    @Test
    public void authTest() {
        //http://glb.qiniucdn.com/abba8d37b7aaf5ee903fd93bb3125e29?imageMogr2/thumbnail/300x300/interlace/1&e=1510385493&token=3cYRoEh67hh3wAsX6ex9FXqk1fT8heJvl43iffYt:50OiQRKg4eN7PdKCaixteuPiJKY=
        initChar();
        // TODO: 2017/11/15 url路径不对。。无法测试
        String url = "?e=1513236176";
        String accessKey = "3cYRoEh67hh3wAsX6ex9FXqk1fT8heJvl43iffYt";
        String secretKey = "9m8cbyGlVKVkXIA_hsr-6rRVD1oEdFf3ndD5robw";
        String sign = "cqFqKOtnS3Qs3Q7LBq3tJ4vZqYs=";

        int index = 0;
        int duindex = 0;
        while (true){
            index++;
            if(index==Integer.MAX_VALUE){
                index = 0;
                duindex++;
                System.out.println("INT最大数" + Integer.MAX_VALUE + "循环次数：" + duindex);
            }
            secretKey = getRandomKey();
            Auth auth = Auth.create(accessKey, secretKey);
            String token = auth.sign(url);
            //System.out.println("token: " + token);
            if (token.endsWith(sign)) {
                System.out.println("发现SECRET_KEY: " + secretKey);
                break;
            }/*else {
                System.out.println(secretKey);
            }*/
        }
    }

    private void initChar(){
        chars[0] = '-';
        chars[1] = '_';
        for (int i = 2; i < chars.length; i++) {
            if(i < 12){
                chars[i] = (char)(46 +i);
            }else if(i < 38){//
                chars[i] = (char)(53 +i);
            }else {//2+10+26
                chars[i] = (char)(59 +i);
            }
        }

    }

    public String getRandomKey() {
        builder.setLength(0);
        for (int i = 0; i < 40; i++) {
            builder.append(chars[rdm.nextInt(40)]);
        }
        return builder.toString();
    }
}
