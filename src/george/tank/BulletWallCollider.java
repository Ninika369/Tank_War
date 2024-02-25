package george.tank;

/**
 * @Author: George Sun
 * @Date: 2024-02-20-19:41
 * @Description: george.tank
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameOject g1, GameOject g2) {
        if (g1 instanceof Bullet && g2 instanceof Wall) {
            Bullet b = (Bullet) g1;
            Wall w = (Wall) g2;
            if (b.getRect().intersects(w.getRect())) {
                b.die();
            }
        }
        else if (g2 instanceof Bullet && g1 instanceof Wall) {
            return collide(g2, g1);
        }
        return true;
    }
}
