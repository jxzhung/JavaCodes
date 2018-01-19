package core.base.other;

import com.jzhung.demo.core.util.FileUtil;
import org.junit.Test;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jzhung on 2017/11/8.
 */
public class WordTest {

    @Test
    public void clearContent() {
        String file = "E:\\Data\\test\\3500words.txt";
        String content = FileUtil.getFileContent(file);
        StringTokenizer st = new StringTokenizer(content, "\n");


        Pattern ptn = Pattern.compile("(.*)?\\s*\\[(.*)?]\\s*(.*?\\.)\\s*(.*)?");
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            if (line.length() < 5 || !line.contains(" ")) {
                continue;
            }
            Matcher matcher = ptn.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1).replace(" ", "").trim();
                String sympol = matcher.group(2).trim();
                String type = matcher.group(3).trim();
                String meaning = matcher.group(4).trim();
                System.out.println(word + "#" + sympol + "#" + type + "#" + meaning + "#" +word);
                if (word.length() > 4) {

                }
            } else {
                //System.out.println("--------------无法识别：" + line);
            }
        }
    }
}
