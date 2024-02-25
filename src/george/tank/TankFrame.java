package george.tank;


import george.tank.Bullet;
import george.tank.Direction;
import george.tank.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: George Sun
 * @Date: 2023-12-24-16:49
 * @Description: george
 */
public class TankFrame extends Frame {

    static final int game_width = PropertyMgr.getInt("gameWidth");
    static final int game_height = PropertyMgr.getInt("gameHeight");

    private int count = 0;

    GameModel gm = GameModel.getInstance();


    public TankFrame() {
        setSize(game_width, game_height);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // To avoid flashes when tank or bullets move
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(game_width, game_height);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, game_width, game_height);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    // 窗口需要重新绘制时（出现，改变大小等等情况），自动调用此方法
    // g相当于系统递给coder的一支画笔
    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }


    class MyKeyListener extends KeyAdapter {

        boolean bL, bU, bR, bD;

        // works when some buttons are pressed
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setTankDir();
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        // works when some buttons are released
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    gm.getMyTank().fire();
                    break;
                default:
                    break;
            }
            setTankDir();
        }


        private void setTankDir() {
            Tank myTank = gm.getMyTank();

            if (bL || bR || bU || bD)
                myTank.setMoving(true);
            else
                myTank.setMoving(false);

            if (bL)
                myTank.setDir(Direction.LEFT);
            if (bR)
                myTank.setDir(Direction.RIGHT);
            if (bU)
                myTank.setDir(Direction.UP);
            if (bD)
                myTank.setDir(Direction.DOWN);


        }


    }

}
