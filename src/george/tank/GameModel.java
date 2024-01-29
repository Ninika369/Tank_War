package george.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: George Sun
 * @Date: 2024-01-29-14:01
 * @Description: george.tank
 */
public class GameModel {

    Tank myTank = new Tank(200, 400, Direction.DOWN, george.tank.Type.GOOD, this);

    List<Bullet> bullets = new ArrayList<Bullet>();

    // enemies a tank have
    List<Tank> enemies = new ArrayList<>();

    // list to contain explosions when tanks die
    List<Explosion> explosions = new ArrayList<>();
    
    public GameModel() {
        for (int i = 0; i < PropertyMgr.getInt("enemiesCount"); i++) {
            enemies.add(new Tank(50 + i * 80, 200, Direction.DOWN, Type.BAD, this));
        }
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量: "+bullets.size(), 10, 120);
        g.drawString("敌人的数量: "+enemies.size(), 10, 60);
        g.drawString("爆炸的数量: "+explosions.size(), 10, 90);

        g.setColor(c);


        myTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                Tank enemy = enemies.get(j);
                bullets.get(i).collide(enemy);

            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).paint(g);
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
