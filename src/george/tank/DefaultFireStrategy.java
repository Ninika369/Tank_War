package george.tank;

/**
 * @Author: George Sun
 * @Date: 2024-01-15-13:17
 * @Description: george.tank
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.width / 2 - Bullet.width / 2;
        int by = t.getY() + Tank.height / 2 - Bullet.height / 2 + 3;
        t.getTf().getAf().createBullet(bx, by, t.getDir(), t.getType(), t.getTf());
        if(t.getType() == Type.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
