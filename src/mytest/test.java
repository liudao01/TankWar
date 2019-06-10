package mytest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.junit.Test;

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
