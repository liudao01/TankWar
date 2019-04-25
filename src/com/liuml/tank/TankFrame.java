package com.liuml.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200;
    int y = 200;


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
    }

    class MykeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        //根据方向 处理坦克的坐标

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
            if (bL) {
                x -= 30;
            }
            if (bU) {
                y -= 30;
            }
            if (bR) {
                x += 30;
            }
            if (bD) {
                y += 30;
            }

            if (x < 0) {
                x = 0;
            }
            if (x > 800 - 50) {
                x = 800 - 50;
            }
            if (y < 0) {
                y = 0;
            }
            if (y > 600 - 50) {
                y = 600-50;
            }

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


        }
    }

}
