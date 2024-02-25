package george.tank;

/**
 * @Author: George Sun
 * @Date: 2024-02-20-19:42
 * @Description: george.tank
 */
public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameOject g1, GameOject g2) {
        if (g1 instanceof Tank && g2 instanceof Wall) {
            Tank t = (Tank) g1;
            Wall w = (Wall) g2;
            if (t.getRect().intersects(w.getRect())) {
                t.back();
            }
        }
        else if (g2 instanceof Tank && g1 instanceof Wall) {
            return collide(g2, g1);
        }
        return true;
    }
}
