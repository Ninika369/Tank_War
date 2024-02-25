package george.test;

import george.tank.Bullet;
import george.tank.T;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author: George Sun
 * @Date: 2023-12-27-16:55
 * @Description: george.test
 */
public class ImageTest {
    @Test
    public void test() {
//        try {
//            BufferedImage image = ImageIO.read(T.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
//            assertNotNull(image);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String s = "1, 2, 3";
        String[] ss = s.split(",");
        for (String str : ss) {
            System.out.println(str);
        }
    }

}
