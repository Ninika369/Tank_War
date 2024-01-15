package george.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-15:44
 * @Description: george
 */
public class Tank {
    private int x, y;
    private Direction dir = Direction.DOWN;
    private static final int speed = PropertyMgr.getInt("tankSpeed");
    private boolean moving = true;
    private TankFrame tf;

    private Type type;

    // used to contain tanks
    private Rectangle rect = new Rectangle();

    private boolean alive = true;

    // used to set the speed of bullets fired by enemies
    private Random random = new Random();

    public static int height = ResourceMgr.goodTankD.getHeight();
    public static int width = ResourceMgr.goodTankD.getWidth();

    FireStrategy fs;

    public Tank(int x, int y, Direction dir, Type type, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;

        if (type == Type.GOOD) {
            String goodFSName = (String) PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            String badFSName = (String) PropertyMgr.get("badFS");
            try {
                fs = (FireStrategy) Class.forName(badFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                g.drawImage(this.getType() == Type.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.getType() == Type.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.getType() == Type.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.getType() == Type.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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


        if (random.nextInt(100) >= 95 && getType() == Type.BAD)
            fire();
        if (getType() == Type.BAD && random.nextInt(100) >= 95)
            this.dir = Direction.values()[random.nextInt(4)];

        boundsCheck();

        rect.x = x;
        rect.y = y;
    }

    private void boundsCheck() {
        if (this.x < 0)
            x = 0;
        if (this.y < 30)
            y = 30;
        if (this.x > TankFrame.game_width - Tank.width)
            x = TankFrame.game_width - Tank.width;
        if (this.y > TankFrame.game_height - Tank.height)
            y = TankFrame.game_height - Tank.height;
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
        fs.fire(this);

    }

    public void die() {
        setAlive(false);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Rectangle getRect() {
        return rect;
    }

    public TankFrame getTf() {
        return tf;
    }
}
