package com.liuml.tank;


import com.liuml.tank.util.LogUtils;

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
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEITHT = 600;
    private Tank tank;
    private Bullet bullet;
     List<Bullet> bulletList = new ArrayList<Bullet>();;

    public TankFrame() {


        // TODO Auto-generated constructor stub
        setSize(FRAME_WIDTH, FRAME_HEITHT);
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

        tank = new Tank(x, y, Direction.RIGHT, this);
    }

    @Override
    public void paint(Graphics graphics) {
        tank.paint(graphics);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
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
                    LogUtils.debug("按下发射");
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
