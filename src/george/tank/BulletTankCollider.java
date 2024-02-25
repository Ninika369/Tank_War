package george.tank;

/**
 * @Author: George Sun
 * @Date: 2024-01-29-19:25
 * @Description: george.tank
 */
public class BulletTankCollider implements Collider{


    @Override
    public boolean collide(GameOject g1, GameOject g2) {
        if (g1 instanceof Bullet && g2 instanceof Tank) {
            Bullet b = (Bullet) g1;
            Tank t = (Tank) g2;
            if (b.getType() == t.getType())
                return true;
            if (b.getRect().intersects(t.getRect())) {
                b.die();
                t.die();
                int eX = t.getX() + Tank.width/2 - Explosion.width/2;
                int eY = t.getY() + Tank.height/2 - Explosion.height/2;
                new Explosion(eX, eY);
            }
        }
        else if (g2 instanceof Bullet && g1 instanceof Tank) {
            return collide(g2, g1);
        }
        return true;
    }
}
