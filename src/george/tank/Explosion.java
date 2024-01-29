package george.tank;


import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2023-12-26-17:03
 * @Description: george
 */
public class Explosion {
    public static int width = ResourceMgr.explodes[0].getWidth();
    public static int height = ResourceMgr.explodes[0].getHeight();


    GameModel gm;

    private int x, y;

    private boolean alive = true;

    private int step = 0;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Explosion(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        new Audio("audio/explode.wav").play();
    }


    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length)
            gm.explosions.remove(this);

    }


    public void die() {
        setAlive(false);
    }
}
