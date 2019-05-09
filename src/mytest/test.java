package mytest;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * desc
 * Created by liuml.
 * Created time 2019/4/30.
 */
public class test {


    @Test
    public void test() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("E:\\testImage.jpg"));
//            Assert.assertNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


