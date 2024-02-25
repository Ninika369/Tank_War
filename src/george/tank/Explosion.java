package george.tank;


import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Explosion extends GameOject {
    public static int width = ResourceMgr.explodes[0].getWidth();
    public static int height = ResourceMgr.explodes[0].getHeight();

    private boolean alive = true;

    private int step = 0;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        new Audio("audio/explode.wav").play();
        GameModel.getInstance().add(this);
    }


    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length)
            GameModel.getInstance().remove(this);

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }


    public void die() {
        setAlive(false);
    }
}
