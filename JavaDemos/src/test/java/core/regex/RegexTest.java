package core.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jzhung on 2016/11/20.
 */
public class RegexTest {
    @Test
    public void test1(){
        Pattern mMenuPtn = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
        String line = "75,生蚝.,生蚝,,,,";
        Matcher matcher = mMenuPtn.matcher(line);
        System.out.println(matcher.group());
    }
}
