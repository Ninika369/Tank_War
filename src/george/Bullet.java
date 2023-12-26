package george;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Bullet {
    private static final int speed = 5;
    private static int width = 20;
    private static int height = 20;

    private TankFrame tf;

    private int x, y;

    private Direction dir;

    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Bullet(int x, int y, Direction dir, TankFrame tf) {
        this.x = x + 10;
        this.y = y + 10;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if (!isAlive)
            tf.bullets.remove(this);

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
        g.setColor(c);

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.game_width || y > TankFrame.game_height)
            isAlive = false;
    }
}
