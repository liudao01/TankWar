package com.liuml.tank;

import com.liuml.tank.util.LogUtils;

import java.awt.*;

/**
 * desc
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Tank {
    int x = 100;
    int y = 100;

    int speed = 5;//速度
    Direction direciton;
    public boolean isMoveing = true;

    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
        move();
    }

    public void setMoveing(boolean moveing) {
        isMoveing = moveing;
    }

    private void move() {
        LogUtils.debug("isMoveing = "+isMoveing);
        if (!isMoveing) {
            return;
        }
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
}
