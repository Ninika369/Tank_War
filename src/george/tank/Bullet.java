package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Bullet extends GameOject {
    private static final int speed = PropertyMgr.getInt("bulletSpeed");
    public static int width = ResourceMgr.bulletD.getWidth();
    public static int height = ResourceMgr.bulletD.getHeight();

    private Type type;


    public Rectangle getRect() {
        return rect;
    }


    // used to contain bullets created by tanks
    private Rectangle rect = new Rectangle();

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

    public Bullet(int x, int y, Direction dir, Type type) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = width;
        rect.height = height;
        GameModel.getInstance().add(this);
    }


    public void paint(Graphics g) {
        if (!isAlive()) {
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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


    public void die() {
        setAlive(false);
    }
}
