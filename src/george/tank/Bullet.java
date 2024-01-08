package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Bullet {
    private static final int speed = PropertyMgr.getInt("bulletSpeed");
    public static int width = ResourceMgr.bulletD.getWidth();
    public static int height = ResourceMgr.bulletD.getHeight();

    private Type type;

    private TankFrame tf;

    private int x, y;

    // used to contain bullets created by tanks
    Rectangle rect = new Rectangle();

    private Direction dir;

    private boolean alive = true;

    public Type getType() {
        return this.type;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Bullet(int x, int y, Direction dir, Type type, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = width;
        rect.height = height;
    }


    public void paint(Graphics g) {
        if (!isAlive()) {
            tf.bullets.remove(this);
        }

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

        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.game_width || y > TankFrame.game_height)
            die();
    }


    public void collide(Tank tank) {
        if (this.getType() == tank.getType())
            return;
        if (rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.width/2 - Explosion.width/2;
            int eY = tank.getY() + Tank.height/2 - Explosion.height/2;
            tf.explosions.add(new Explosion(eX, eY, tf));
        }
    }


    public void die() {
        setAlive(false);
    }
}
