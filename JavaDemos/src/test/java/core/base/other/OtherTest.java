package core.base.other;

import com.jzhung.demo.core.util.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jzhung on 2017/3/29.
 */
public class OtherTest {

    public static List<File> filelist = new ArrayList<File>();

    @Test
    public void uuid() {
        for (int i = 0; i < 23; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            System.out.println(uuid);
        }
    }

    @Test
    public void unRar() throws Exception {
        String dir = "E:\\book\\世界名著\\";
        getFiles(dir);

        for (File file : filelist) {
            String name = file.getName();
            System.out.println(file.getAbsolutePath());
            if (name.contains("_下书网wwww.xiabook.com")) {
                String newName = name.replace("_下书网wwww.xiabook.com", "");
                file.renameTo(new File(file.getParentFile().getAbsolutePath(), newName));
            }
            /*if(file.getName().endsWith(".rar")){
                file.delete();
            }*/
            //unZip(file);
        }

    }

    public void unZip(File allZipList) throws Exception {
        if (allZipList.getName().endsWith(".rar")) {
            unRarFile(allZipList.getParentFile().getAbsolutePath() + "\\", allZipList.getAbsolutePath());
        }
    }

    public void getFiles(String filePath) throws Exception {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                /*if (file.getName().endsWith(".rar") || file.getName().endsWith(".zip")) {
                    System.out.println(file.getAbsolutePath());
                    filelist.add(file);
                }*/
                filelist.add(file);
            } else {
                getFiles(file.getAbsolutePath());
            }
        }
    }

    public void unRarFile(String targetPath, String absolutePath) throws Exception {
        System.out.println("开始解压Rar:" + absolutePath + "  到: " + targetPath);
        String cmd = "C:\\Program Files\\WinRAR\\WinRAR.exe";
        String unrarCmd = cmd + " x -r -p- -o+ \"" + absolutePath + "\" " + targetPath;
        Runtime rt = Runtime.getRuntime();
        Process pre = rt.exec(unrarCmd);
        InputStreamReader isr = new InputStreamReader(pre.getInputStream());
        BufferedReader bf = new BufferedReader(isr);
        String line = null;
        while ((line = bf.readLine()) != null) {
            line = line.trim();
            if ("".equals(line)) {
                continue;
            }
        }
        System.out.println("成功解压:" + new File(absolutePath).getName());
        bf.close();
    }

    @Test
    public void testDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddaHH_mm_ss");
        String date = df.format(new Date());
        System.out.println(date);
    }

    @Test
    public void moveFiles() {
        String targetDir = "/home/800G/old_demo_two/upload/speakEnglish/upload/zip/";
        String srcBaseDir = "/home/800G/old_demo_two";
        String fileList = "d:\\se_scene.txt";

        String fileContent = FileUtil.getFileContent(fileList);
        StringTokenizer st = new StringTokenizer(fileContent, "\n");
        String line;
        StringBuilder moveBuilder = new StringBuilder();
        StringBuilder moveSqlBuilder = new StringBuilder();
        while (st.hasMoreTokens()) {
            line = st.nextToken();
            StringTokenizer linest = new StringTokenizer(line, "#");
            String scenceId = linest.nextToken();
            linest.nextToken();
            String path = linest.nextToken();
            String src = srcBaseDir + path;
            String target = targetDir + scenceId + ".zip";
            //srcFile.renameTo(targetFile);
            moveBuilder.append("mv " + src.replace("\\", "/") + " " + target + "\n");
            String newPath = String.format("update se_scene set downloadUrl = '%s' where sceneId=%s", "/speakEnglish/upload/zip/" + scenceId + ".zip", scenceId);
            moveSqlBuilder.append(newPath + ";\n");
        }
        System.out.println(moveBuilder.toString());
        System.out.println();
        System.out.println(moveSqlBuilder.toString());

    }

    @Test
    public void testPadPwd(){
        Date date = new Date(System.currentTimeMillis());
        System.out.println(isRight(date, "039"));
        System.out.println(isRight(date, "050"));
        System.out.println(isRight(date, "051"));
    }

    /**
     * 验证输入的密码对错
     *
     * @param date
     *            弹出对话框记录的日期
     * @param key
     *            输入的密码
     * @return 密码是否正确
     */
    private static boolean isRight(Date date, String key) {
        if ("".equals(key)) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        int miniTenNum = minute / 10; // 分钟十位上的数字
        int miniFirstNum = minute % 10; // 分钟个位上的数字
        int hourFiestNum = hour % 10; // 小时个位上的数字
        // 验证，直接验证中间的时间对不对
        String rightKey = "" + miniTenNum + hourFiestNum + miniFirstNum;
        int rightKeyIndex = key.indexOf(rightKey, miniTenNum); // 密码关键字在用户输入中的位置

        if (key.contains(rightKey) && rightKeyIndex == miniTenNum) {
            return true;
        }
        return false;
    }

    @Test
    public void test(){
        String pass = "18656";
        char[] chars = pass.toCharArray();
        for (char c : chars) {
            System.out.println(c);
            System.out.println(c + 1);
            System.out.println(Integer.valueOf(c + "") + 1);
            System.out.println("---------");
        }
    }

    @Test
    public void testUrl() throws UnsupportedEncodingException {
        //http://192.168.1.126:8080/upload/2017/1215/c462b1e7-629a-4419-bd4c-c678a9d91c03-知识点15  标点符号--微课.mp4
        //http://192.168.1.126:8080/upload/2017/1215/c462b1e7-629a-4419-bd4c-c678a9d91c03-%E7%9F%A5%E8%AF%86%E7%82%B915%20%20%E6%A0%87%E7%82%B9%E7%AC%A6%E5%8F%B7--%E5%BE%AE%E8%AF%BE.mp4

        String url = "http://192.168.1.126:8080/upload/2017/1215/c462b1e7-629a-4419-bd4c-c678a9d91c03-知识点15  标点符号--微课.mp4";
        String url2 = "http://192.168.1.126:8080/upload/2017/1215/c462b1e7-629a-4419-bd4c-c678a9d91c03-%E7%9F%A5%E8%AF%86%E7%82%B915%20%20%E6%A0%87%E7%82%B9%E7%AC%A6%E5%8F%B7--%E5%BE%AE%E8%AF%BE.mp4";

        int index = url.lastIndexOf("/");
        String preStr = url.substring(0, index + 1);
        String afterStr = url.substring(index + 1);
        String en1 = URLEncoder.encode(afterStr, "utf-8");
        en1 = en1.replace("+", "%20");
        System.out.println(preStr);
        System.out.println(afterStr);
        System.out.println(preStr + en1);
        System.out.println(encodeUrl(url));
        System.out.println(url2);
    }

    public static String encodeUrl(String url) throws UnsupportedEncodingException {
        int index = url.lastIndexOf("/");
        String preStr = url.substring(0, index + 1);
        String afterStr = url.substring(index + 1);
        String en1 = URLEncoder.encode(afterStr, "utf-8");
        en1 = en1.replace("+", "%20");
        return preStr + en1;
    }
}
