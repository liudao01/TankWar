package com.liuml.tank;


import com.liuml.tank.util.Constant;
import com.liuml.tank.util.LogUtils;
import com.liuml.tank.util.RandomUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    private int x = 200;
    private int y = 200;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    private Tank tank;
    List<Bullet> bulletList = new ArrayList<Bullet>();
    List<Tank> tankList = new ArrayList<>();
    TankFrame tankFrame;

    public TankFrame() {

        tankFrame = this;
        // TODO Auto-generated constructor stub
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        this.addKeyListener(new MykeyListener());

        tank = new Tank(x, y, null, this);
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        //双缓冲
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        tank.paint(graphics);
        Color color = graphics.getColor();
        graphics.setColor(Color.GREEN);
        graphics.drawString("当前子弹个数" + bulletList.size(), 50, 50);
        graphics.drawString("当前敌方坦克个数" + tankList.size(), 50, 70);
        graphics.setColor(color);
        checkCollision();
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(graphics);
        }

    }

    //碰撞检测
    private void checkCollision() {
        //TODO 明天用个算法把. 今天先把功能实现
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++) {
                int bx = bulletList.get(i).getX();
                int by = bulletList.get(i).getY();
                LogUtils.debug("bx = " + bx + "  by = " + by);
                if (tankList.get(j).isLive()) {
                    int tx = tankList.get(j).getX();
                    int ty = tankList.get(j).getY();
                    LogUtils.debug("坦克 =  tx = " + tx + "  ty = " + ty);
                    if (bx + Constant.BulletWidth > tx && by + Constant.BulletHeight > ty && bx < tx + Constant.tankWidth && by < ty + Constant.tankHeight) {
                        tankList.get(i).setLive(false);
                    }
                }

            }

        }
    }

    class MykeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_ENTER:
                    tankList.add(new Tank(RandomUtil.getRandomHeight(), RandomUtil.getRandomHeight(), null, tankFrame));
                    break;
                default:
                    break;
            }
            getDireciton();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            getDireciton();
        }


        //根据方向 处理坦克的坐标
        private void getDireciton() {
            if (!bL && !bD && !bR && !bU) {
                tank.setMoveing(false);
            } else {
                tank.setMoveing(true);
            }
            if (bL) {
                tank.setDireciton(Direction.LEFT);
            }
            if (bU) {
                tank.setDireciton(Direction.UP);

            }
            if (bR) {
                tank.setDireciton(Direction.RIGHT);
            }
            if (bD) {
                tank.setDireciton(Direction.DOWN);
            }
            if (bL && bU) {
                tank.setDireciton(Direction.LEFT_UP);
            }
            if (bL && bD) {
                tank.setDireciton(Direction.LEFT_DOWN);
            }
            if (bR && bU) {
                tank.setDireciton(Direction.RIGHT_UP);
            }
            if (bR && bD) {
                tank.setDireciton(Direction.RIGHT_DOWN);
            }

        }


    }

}
