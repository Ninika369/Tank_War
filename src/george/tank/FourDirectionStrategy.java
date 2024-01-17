package george.tank;

import george.tank.abstractfactory.BaseBullet;

/**
 * @Author: George Sun
 * @Date: 2024-01-15-15:24
 * @Description: george.tank
 */
public class FourDirectionStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.width / 2 - Bullet.width / 2;
        int by = t.getY() + Tank.height / 2 - Bullet.height / 2 + 3;
        Direction[] dirs = Direction.values();
        for (int i = 0; i < dirs.length; i++) {
            t.getTf().getAf().createBullet(bx, by, dirs[i], t.getType(), t.getTf());

        }

        if(t.getType() == Type.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
