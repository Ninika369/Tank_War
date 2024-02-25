package george.tank;

import george.tank.decorators.RectDecorator;
import george.tank.decorators.TailDecorator;

import java.awt.*;
import java.util.Random;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-15:44
 * @Description: george
 */
public class Tank extends GameOject {

    private int preX, preY;

    private Direction dir = Direction.DOWN;
    private static final int speed = PropertyMgr.getInt("tankSpeed");
    private boolean moving = true;


    private Type type;

    // used to contain tanks
    private Rectangle rect = new Rectangle();

    private boolean alive = true;

    // used to set the speed of bullets fired by enemies
    private Random random = new Random();

    public static int height = ResourceMgr.goodTankD.getHeight();
    public static int width = ResourceMgr.goodTankD.getWidth();

    public Tank(int x, int y, Direction dir, Type type) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;

        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!alive) {
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private void move() {
        preX = x;
        preY = y;
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
        int bx = this.x + Tank.width / 2 - Bullet.width / 2;
        int by = this.y + Tank.height / 2 - Bullet.height / 2 + 3;
        GameModel.getInstance().add(
                new RectDecorator(
                        new TailDecorator(
                                new Bullet(bx, by, dir, getType()))));
        if(this.getType() == Type.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

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

    public int getPreX() {
        return preX;
    }

    public int getPreY() {
        return preY;
    }

    public void back() {
        x = preX;
        y = preY;
    }


}
