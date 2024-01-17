package george.tank.abstractfactory;

import george.tank.Tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-01-16-23:44
 * @Description: george.tank.abstractfactory
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collide(Tank enemy);
}
