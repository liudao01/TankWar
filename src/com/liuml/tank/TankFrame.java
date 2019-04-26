package com.liuml.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 100;
    int y = 100;

    int speed = 3;//速度
    Direction direciton;
    boolean bL = false;
    boolean bU = false;
    boolean bR = false;
    boolean bD = false;

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
        graphics.fillRect(x, y, 50, 50);
        getDireciton();

    }

    //根据方向 处理坦克的坐标
    private void getDireciton() {
        if (bL) {
            direciton = Direction.LEFT;
        }
        if (bU) {
            direciton = Direction.UP;
        }
        if (bR) {
            direciton = Direction.RIGHT;
        }
        if (bD) {
            direciton = Direction.DOWN;
        }
        if (bL && bU) {
            direciton = Direction.LEFT_UP;
        }
        if (bL && bD) {
            direciton = Direction.LEFT_DOWN;
        }
        if (bR && bU) {
            direciton = Direction.RIGHT_UP;
        }
        if (bR && bD) {
            direciton = Direction.RIGHT_DOWN;
        }

        move();
    }

    private void move() {
        if (direciton != null) {
            switch (direciton) {
                case UP:
                    y -= speed;
                    break;
                case DOWN:
                    y += speed;
                    break;
                case LEFT:
                    x -= speed;
                    break;
                case RIGHT:
                    x += speed;
                    break;
                case LEFT_UP:
                    y -= speed;
                    x -= speed;
                    break;
                case LEFT_DOWN:
                    x -= speed;
                    y += speed;
                    break;
                case RIGHT_UP:
                    y -= speed;
                    x += speed;
                    break;
                case RIGHT_DOWN:
                    x += speed;
                    y += speed;
                    break;
            }
        }
        if (x < 0) {
            x = 0;
        }
        if (x > 800 - 50) {
            x = 800 - 50;
        }
        if (y < 20) {
            y = 20;
        }
        if (y > 600 - 50) {
            y = 600 - 50;
        }
    }

    class MykeyListener extends KeyAdapter {


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
                case KeyEvent.VK_SPACE:
                    direciton = null;
                    break;
                default:
                    break;
            }
            getDireciton();
            repaint();
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
            if (bL) {
                x -= speed;
            }
            if (bU) {
                y -= speed;
            }
            if (bR) {
                x += speed;
            }
            if (bD) {
                y += speed;
            }


        }
    }

}
