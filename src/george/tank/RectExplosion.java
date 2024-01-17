package george.tank;

import george.tank.abstractfactory.BaseExplosion;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-01-17-00:26
 * @Description: george.tank
 */
public class RectExplosion extends BaseExplosion {

    public static int width = ResourceMgr.explodes[0].getWidth();
    public static int height = ResourceMgr.explodes[0].getHeight();


    private TankFrame tf;

    private int x, y;

    private boolean alive = true;

    private int step = 0;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public RectExplosion(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").play();
    }


    @Override
    public void paint(Graphics g) {

//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10*step);
        step++;

        if (step >= 10)
            tf.explosions.remove(this);

        g.setColor(c);
    }


    public void die() {
        setAlive(false);
    }
}
