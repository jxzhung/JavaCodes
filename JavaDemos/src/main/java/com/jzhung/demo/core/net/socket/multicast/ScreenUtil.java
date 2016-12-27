package com.jzhung.demo.core.net.socket.multicast;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jzhung on 2016/12/26.
 */
public class ScreenUtil {

    @Test
    public void getScreen() throws AWTException, IOException, InterruptedException {
        int index = 0;
        while(true){
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(d.width, d.height));
            ImageIO.write(bufferedImage, "png", new File("E:\\data\\screen\\", System.currentTimeMillis() + ".jpg"));
            System.out.println(index + "ok");
            Thread.sleep(200);
            index ++;
        }

    }
}
