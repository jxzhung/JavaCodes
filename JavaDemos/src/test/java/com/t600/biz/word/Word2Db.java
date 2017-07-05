package com.t600.biz.word;

import com.jzhung.demo.core.util.FileUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jzhung on 2017/6/29.
 */
public class Word2Db {

    @Test
    public void parseWordTxt() {
        String word = "e:\\word.txt";
        String lineRegex = "(\\w+)\\s+(n|adv|adj|prep|art|v|num|conj|pron)\\.(.*)";
        Pattern ptn = Pattern.compile(lineRegex);
        String fileContent = FileUtil.getFileContent(word);
        //System.out.println(fileContent);
        StringTokenizer st = new StringTokenizer(fileContent, "\n");
        String line;
        int lineNum = 1;
        List<Word> wordList = new ArrayList<Word>();
        while (st.hasMoreTokens()) {
            line = st.nextToken();
            //字母节点
            if (line.length() == 1 && line.matches("\\S")) {
                //System.out.println("行号" + lineNum + "：" + line);
            }else if(line.matches("^[a-zA-Z].*") && !line.startsWith("e.g.") && !line.startsWith("eg:")){
                if(line.contains("art.") || line.contains("n.") || line.contains("v.") || line.contains("num.")|| line.contains("adj")|| line.contains(" n") || line.contains("adv") || line.contains("prep") || line.contains("conj")|| line.contains("pron")){
                    //System.out.println("行号" + lineNum + "：" + line);
                    //System.out.println(line);
                    Matcher matcher = ptn.matcher(line);
                    if(matcher.find()){
                        /*System.out.println(matcher.group(1).trim());
                        System.out.println(matcher.group(2).trim());
                        System.out.println(matcher.group(3).trim());*/
                        Word w = new Word();
                        w.spell = matcher.group(1).trim();
                        w.type = matcher.group(2).trim();
                        String meaning = matcher.group(3).trim().replace(" ", "");
                        int index = -1;
                        if((index = meaning.indexOf("e.g.")) != -1){
                            meaning = meaning.substring(0, index);
                        }
                        if((index = meaning.indexOf("短语")) != -1){
                            meaning = meaning.substring(0, index);
                        }
                        w.meaning = meaning;
                        wordList.add(w);
                        System.out.println(w);
                    }
                }else {
                    //System.out.println("行号" + lineNum + "：" + line);
                }
            }
            lineNum++;
        }

    }

    class Word{
        String spell;
        String type;
        String meaning;

        @Override
        public String toString() {
            return spell + '#' + type + '#' + meaning;
        }
    }
}
