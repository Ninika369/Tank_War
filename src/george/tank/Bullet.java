package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Bullet {
    private static final int speed = 10;
    public static int width = ResourceMgr.bulletD.getWidth();
    public static int height = ResourceMgr.bulletD.getHeight();

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
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if (!isAlive)
            tf.bullets.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

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


    public void collide(Tank tank) {
        Rectangle b = new Rectangle(x, y, width, height);
        Rectangle t = new Rectangle(tank.getX(), tank.getY(), Tank.width, Tank.height);
        if (b.intersects(t)) {
            tank.die();
            die();
        }
    }


    public void die() {
        setAlive(false);
    }
}
