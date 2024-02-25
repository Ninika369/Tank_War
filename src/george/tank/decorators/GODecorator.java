package george.tank.decorators;

import george.tank.GameOject;

import java.awt.*;

/**
 * @Author: George Sun
 * @Date: 2024-02-25-17:22
 * @Description: george.tank.decorators
 */
public abstract class GODecorator extends GameOject {

    GameOject go;
    public GODecorator(GameOject go) {
        this.go = go;
    }


    public abstract void paint(Graphics g);


}
