package george.tank.abstractfactory;

import george.tank.*;

/**
 * @Author: George Sun
 * @Date: 2024-01-16-23:41
 * @Description: george.tank.abstractfactory
 */
public class DefaultFactory extends AbstractFactory{
    @Override
    public BaseTank createTank(int x, int y, Direction dir, Type type, TankFrame tf) {
        return new Tank(x, y, dir, type, tf);
    }

    @Override
    public BaseExplosion createExplode(int x, int y, TankFrame tf) {
        return new Explosion(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Type type, TankFrame tf) {
        return new Bullet(x, y, dir, type, tf);
    }


    //Singleton strategy
    private static class DefaultHandler{
        private static AbstractFactory instance = new DefaultFactory();
    }

    private DefaultFactory(){
        if(DefaultHandler.instance != null){
            throw new RuntimeException("Not Allowed!");
        }
    }

    public static AbstractFactory getInstance(){
        return DefaultHandler.instance;
    }
}
