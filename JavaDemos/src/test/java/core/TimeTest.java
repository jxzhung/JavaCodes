package core;

import org.junit.Test;

/**
 * Created by Jzhung on 2018/3/2.
 */
public class TimeTest {
    public static String tmp = "%d时%d分%d秒";

    @Test
    public void getTime(){
        System.out.println(getTimeStr("3710000"));
    }

    public static String getTimeStr(String time) {
        if (time == null || time.equals("")) {
            return String.format(tmp, 0, 0, 0);
        }
        long t = Long.valueOf(time);
        t = t/1000;
        int hour = (int) (t / 3600);
        int minute = (int) (t % 3600) / 60;
        int seconds = (int) (t - (hour * 3600) - (minute * 60)) % 60;
        return String.format(tmp, hour, minute, seconds);
    }
}
