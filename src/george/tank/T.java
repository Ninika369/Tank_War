package george.tank;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: George Sun
 * @Date: 2023-12-24-15:40
 * @Description: george
 */
public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();


        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
