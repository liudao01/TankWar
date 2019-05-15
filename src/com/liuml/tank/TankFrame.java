package com.liuml.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.liuml.tank.GameMode.GAME_HEIGHT;
import static com.liuml.tank.GameMode.GAME_WIDTH;

public class TankFrame extends Frame {


    GameMode gameMode;
    Tank tank;

    public TankFrame() {

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

        gameMode = new GameMode();
        tank =  gameMode.tank;
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

        gameMode.paint(graphics);
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
                    gameMode.addEnemyTank();
                    break;
                case KeyEvent.VK_F1:
                    gameMode.tank.Resurrection();
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
                    gameMode.tank.fire();
                    break;
                case KeyEvent.VK_F:
                    tank.fire();
                    break;
                case KeyEvent.VK_P:
                    if (tank.fireType == 1) {
                        tank.fireType = 2;
                    } else {
                        tank.fireType = 1;
                    }
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
                tank.setDirection(Direction.LEFT);
            }
            if (bU) {
                tank.setDirection(Direction.UP);
            }
            if (bR) {
                tank.setDirection(Direction.RIGHT);
            }
            if (bD) {
                tank.setDirection(Direction.DOWN);
            }
            if (bL && bU) {
                tank.setDirection(Direction.LEFT_UP);
            }
            if (bL && bD) {
                tank.setDirection(Direction.LEFT_DOWN);
            }
            if (bR && bU) {
                tank.setDirection(Direction.RIGHT_UP);
            }
            if (bR && bD) {
                tank.setDirection(Direction.RIGHT_DOWN);
            }

        }


    }

}
