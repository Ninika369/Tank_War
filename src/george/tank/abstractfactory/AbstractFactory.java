package george.tank.abstractfactory;

import george.tank.Direction;
import george.tank.TankFrame;
import george.tank.Type;

/**
 * @Author: George Sun
 * @Date: 2024-01-16-23:31
 * @Description: george.tank.abstractfactory
 */

// Try Abstract Strategy
public abstract class AbstractFactory {
    public abstract BaseTank createTank(int x, int y, Direction dir, Type type, TankFrame tf);
    public abstract BaseExplosion createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Direction dir, Type type, TankFrame tf);
}
