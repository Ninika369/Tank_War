package george.tank;

/**
 * @Author: George Sun
 * @Date: 2024-01-29-19:25
 * @Description: george.tank
 */
public class TankTankCollider implements Collider{


    @Override
    public boolean collide(GameOject g1, GameOject g2) {
        if (g1 instanceof Tank && g2 instanceof Tank) {
            Tank t1 = (Tank) g1;
            Tank t2 = (Tank) g2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
            }
        }
        return true;
    }
}
