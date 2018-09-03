package ffmpeg;

import com.jzhung.util.FFmpegUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jzhung on 2017/9/9.
 */
public class FFmpegTest {

    @Test
    public void amr2mp3(){
        String src = "d:\\test.amr";
        String dst = "d:\\test.mp3";
        try {
            FFmpegUtil.arm2mp3(src, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
