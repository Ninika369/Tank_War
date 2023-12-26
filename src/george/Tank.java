package george;

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
        g.fillRect(x,y, 50,50);
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
        tf.bullets.add(new Bullet(x, y, dir, tf));

    }
}
