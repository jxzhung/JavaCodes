package core.other;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jzhung on 2017/12/1.
 */
public class DataFileUtil {

    /**
     * 通过参数获取路径
     * /科目/UUID/1/学号
     *
     * @param baseDir
     * @param uuid
     * @param lesson
     * @param studentId
     * @param version
     * @return
     */
    public static String getPath(String baseDir, String uuid, String lesson, String studentId, String version){
        File target = new File(baseDir, "\\" + lesson + "\\" + uuid + "\\" + version + "\\" + studentId + ".zip");
        return target.getAbsolutePath();
    }

    /**
     * 通过路径获取参数
     * @param baseDir
     * @param FilePath
     * @return
     */
    public static Map<String, String> getParams(String baseDir, String FilePath){
        baseDir = baseDir.replace("\\", "/");
        if(!baseDir.endsWith("/")){
            baseDir = baseDir + "/";
        }
        FilePath = FilePath.replace("\\", "/");
        String halfPath = FilePath.replace(baseDir, "");
        String[] split = halfPath.split("/");
        for (String s : split) {
            System.out.println("--" + s);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("lesson" , split[0]);
        map.put("uuid" , split[1]);
        map.put("version" , split[2]);
        map.put("studentId" , split[3].substring(0, split[3].indexOf(".")));
        return map;
    }

    public static void main(String[] args) {
        String base = "F:\\迅雷下载\\";
        String fullPath = "F:\\迅雷下载\\数学\\ASDJLASJDKLASD1231\\1\\70035.zip";
        Map<String, String> params = getParams(base, fullPath);

        String lesson = params.get("lesson");
        String uuid = params.get("uuid");
        String version = params.get("version");
        String studentId = params.get("studentId");
        String path = getPath(base, uuid, lesson, studentId, version);
        System.out.println(path);
        System.out.println(new File(path).exists());
    }
}
