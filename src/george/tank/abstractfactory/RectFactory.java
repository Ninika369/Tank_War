package george.tank.abstractfactory;

import george.tank.*;

/**
 * @Author: George Sun
 * @Date: 2024-01-17-00:27
 * @Description: george.tank.abstractfactory
 */
public class RectFactory extends AbstractFactory{

    @Override
    public BaseTank createTank(int x, int y, Direction dir, Type type, TankFrame tf) {
        return new Tank(x, y, dir, type, tf);
    }

    @Override
    public BaseExplosion createExplode(int x, int y, TankFrame tf) {
        return new RectExplosion(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Type type, TankFrame tf) {
        return new RectBullet(x, y, dir, type, tf);
    }

    private static class RectHandler{
        private static AbstractFactory instance = new RectFactory();
    }

    private RectFactory(){
        if(RectHandler.instance != null){
            throw new RuntimeException("Not Allowed!");
        }
    }

    public static AbstractFactory getInstance(){
        return RectHandler.instance;
    }
}
