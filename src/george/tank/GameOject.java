package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-01-29-17:42
 * @Description: george.tank
 */

// Father class of all objects in game
public abstract class GameOject {

    public int x, y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
