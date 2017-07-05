package core.base.other;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jzhung on 2017/3/29.
 */
public class OtherTest {

    public static List<File> filelist = new ArrayList<File>();

    @Test
    public void uuid(){
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
            if(name.contains("_下书网wwww.xiabook.com")){
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

    public void getFiles(String filePath) throws Exception  {
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
    public void testDateFormat(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddaHH_mm_ss");
        String date = df.format(new Date());
        System.out.println(date);
    }

}
