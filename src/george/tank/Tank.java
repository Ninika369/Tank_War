package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-15:44
 * @Description: george
 */
public class Tank {
    private int x, y;
    private Direction dir = Direction.DOWN;
    private static final int speed = 5;
    private boolean moving = false;
    private TankFrame tf;

    private boolean alive = true;

    public static int height = ResourceMgr.tankD.getHeight();
    public static int width = ResourceMgr.tankD.getWidth();

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!alive) {
            tf.enemies.remove(this);
            return;
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        move();
    }


    private void move() {
        if (!isMoving())
            return;
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
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int bx = this.x + Tank.width / 2 - Bullet.width / 2;
        int by = this.y + Tank.height / 2 - Bullet.height / 2 + 3;
        tf.bullets.add(new Bullet(bx, by, dir, tf));

    }

    public void die() {
        alive = false;
    }
}
