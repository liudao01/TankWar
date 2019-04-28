package com.liuml.tank;

import java.awt.*;

/**
 * 子弹类
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Bullet {

    int x = 100;
    int y = 100;

    int speed = 30;//速度
    Direction direciton;


    public Bullet(int x, int y, Direction direciton) {
        this.x = x;
        this.y = y;
        this.direciton = direciton;
    }

    public void paint(Graphics graphics) {
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
//        if (x < 0) {
//            x = 0;
//        }
//        if (x > 800 - 50) {
//            x = 800 - 50;
//        }
//        if (y < 20) {
//            y = 20;
//        }
//        if (y > 600 - 50) {
//            y = 600 - 50;
//        }
    }
}
