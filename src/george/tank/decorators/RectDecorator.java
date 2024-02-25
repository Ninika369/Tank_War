package george.tank.decorators;

import george.tank.Collider;
import george.tank.GameOject;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-02-25-17:59
 * @Description: george.tank.decorators
 */
public class RectDecorator extends GODecorator{
    public RectDecorator(GameOject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, getWidth()+2, getHeight()+2);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
