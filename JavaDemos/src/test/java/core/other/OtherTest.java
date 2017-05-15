package core.other;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jzhung on 2016/12/22.
 */
public class OtherTest {
    private String name;

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
}
