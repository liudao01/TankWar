package com.liuml.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank tank = new Tank();


    public TankFrame() {
        // TODO Auto-generated constructor stub
        setSize(800, 600);
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


    }


    @Override
    public void paint(Graphics graphics) {
        tank.paint(graphics);
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
                default:
                    break;
            }
            getDireciton();
        }


        //根据方向 处理坦克的坐标
        private void getDireciton() {
            if (!bL && !bD && !bR && !bU) {
                tank.isMoveing = false;
            } else {
                tank.isMoveing = true;
            }
            if (bL) {
                tank.direciton = Direction.LEFT;
            }
            if (bU) {
                tank.direciton = Direction.UP;
            }
            if (bR) {
                tank.direciton = Direction.RIGHT;
            }
            if (bD) {
                tank.direciton = Direction.DOWN;
            }
            if (bL && bU) {
                tank.direciton = Direction.LEFT_UP;
            }
            if (bL && bD) {
                tank.direciton = Direction.LEFT_DOWN;
            }
            if (bR && bU) {
                tank.direciton = Direction.RIGHT_UP;
            }
            if (bR && bD) {
                tank.direciton = Direction.RIGHT_DOWN;
            }

        }


    }

}
