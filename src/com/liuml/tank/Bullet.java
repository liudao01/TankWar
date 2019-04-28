package com.liuml.tank;

import java.awt.*;

/**
 * 子弹类
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Bullet {

    private int x = 100;
    private int y = 100;

    private int speed = 30;//速度
    private Direction direciton;
    private TankFrame tankFrame;
    private boolean live = true;//是否存活

    public Bullet(int x, int y, Direction direciton, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direciton = direciton;
        this.tankFrame = tankFrame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics graphics) {
        if (!live) {
            tankFrame.bulletList.remove(this);
        }
        Color color = graphics.getColor();
        graphics.setColor(Color.blue);
        graphics.fillOval(x, y, 20, 20);
        move();
        graphics.setColor(color);
    }

    private void move() {
//        LogUtils.debug("isMoveing = "+isMoveing);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }

    }
}
