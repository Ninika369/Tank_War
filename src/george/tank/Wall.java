package george.tank;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-02-20-16:40
 * @Description: george.tank
 */
public class Wall extends GameOject{

    private int w, h;

    public Rectangle getRect() {
        return rect;
    }

    private Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
