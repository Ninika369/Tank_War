package george.tank;

import com.sun.org.apache.xml.internal.security.Init;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: George Sun
 * @Date: 2024-01-29-14:01
 * @Description: george.tank
 */
public class GameModel {

    private static GameModel gm = new GameModel();

    static {
        gm.init();
    }

    Tank myTank;

    //Define where you would like to store your tanks by yourself
    String address;

//    List<Bullet> bullets = new ArrayList<Bullet>();

    // enemies a tank have
//    List<Tank> enemies = new ArrayList<>();
//
//    // list to contain explosions when tanks die
//    List<Explosion> explosions = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    private List<GameOject> objects = new ArrayList<>();

    //singleton case

    private GameModel() {
    }

    private void init() {


        //initialize player's tank
        myTank = new Tank(200, 400, Direction.DOWN, george.tank.Type.GOOD);

        for (int i = 0; i < PropertyMgr.getInt("enemiesCount"); i++) {
            add(new Tank(50 + i * 80, 200, Direction.DOWN, Type.BAD));
        }
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 150));
    }

    public static GameModel getInstance() {
        return gm;
    }


    public void add(GameOject g) {
        this.objects.add(g);
    }

    public void remove(GameOject oj) {
        this.objects.remove(oj);
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量: "+bullets.size(), 10, 120);
//        g.drawString("敌人的数量: "+enemies.size(), 10, 60);
//        g.drawString("爆炸的数量: "+explosions.size(), 10, 90);

        g.setColor(c);


        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }


        // collide with each other
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameOject o1 = objects.get(i);
                GameOject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }
    }


    public void save() {
        File file = new File(address);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (oos != null) {
                try {
                    oos.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File file = new File(address);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank)ois.readObject();
            objects = (List)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (ois != null) {
                try {
                    ois.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Tank getMyTank() {
        return myTank;
    }
}
