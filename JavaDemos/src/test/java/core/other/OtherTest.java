package core.other;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jzhung on 2016/12/22.
 */
public class OtherTest {
    private String name;
    {
        name = "asd";
    }

    @Test
    public void foo1() {
        foo2(name);
        System.out.println(name);
    }

    private void foo2(String a) {
        a = "1231";
        System.out.println(a);
    }

    @Test
    public void genSQL() {
        String text = "专题";
        int subjectId = 4;
        int unit = 21;
        String template = "INSERT INTO `unit` VALUES (null, '专题%s', " + subjectId + ");\n";
        for (int i = 0; i < unit; i++) {
            System.out.printf(template, numToUpper((i + 1)));
        }
    }

    // 将数字转化为大写
    public static String numToUpper(int num) {
        // String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String u[] = {"十", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }

    @Test
    public void testParse(){
        String html = "html:\"[{\\\"optionHtml\\\":\\\"A、多于4个\\\"},{\\\"optionHtml\\\":\\\"B、4个\\\"},{\\\"optionHtml\\\":\\\"C、3个\\\"},{\\\"optionHtml\\\":\\\"D、2个\\\"}]\"";

        html = html.replace("\"", "").replace("\\optionHtml\\:\\", "").replace("html:[", "").replace("]", "").replace("\\},", "}").replace("\\}", "}");
        String regex ="\\{(.*?)}";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(html);
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void timeTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyDHHmmss");
        long time = System.currentTimeMillis() - (60 * 1000 * 60 * 24 * 291);
        String timeStr1 = sdf.format(new Date());
        String timeStr = sdf.format(new Date(time));
        System.out.println(timeStr1);
        System.out.println(timeStr);
    }

    @Test
    public void timeTest2(){
        long[] todayBeginAndEnd = getTodayBeginAndEnd();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String start = sdf.format(new Date(todayBeginAndEnd[0]));
        String end = sdf.format(new Date(todayBeginAndEnd[1]));
        System.out.println("开始：" + start);
        System.out.println("结束：" + end);

        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        System.out.println(new Timestamp(zero));//今天零点零分零秒
        System.out.println(new Timestamp(twelve));//今天23点59分59秒
    }

    public static long[] getTodayBeginAndEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        long[] time = new long[2];
        time[0] = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        time[1] = calendar.getTimeInMillis();
        return time;
    }
}
