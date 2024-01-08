package george.tank;

/**
 * @Author: George Sun
 * @Date: 2023-12-24-15:40
 * @Description: george
 */
public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(50 + i * 80, 200, Direction.DOWN, Type.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
