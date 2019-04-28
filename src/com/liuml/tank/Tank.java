package com.liuml.tank;

import java.awt.*;

/**
 * 坦克类
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Tank {
    private int x = 100;
    private int y = 100;

    private int speed = 5;//速度
    private Direction direciton;//方向
    public boolean isMoveing = true;//是否移动
    private TankFrame tankFrame = null;

    public Tank(int x, int y, Direction direciton, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direciton = direciton;
        this.tankFrame = tankFrame;
    }

    public void setDireciton(Direction direciton) {
        this.direciton = direciton;
    }

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 50, 50);

        move();
        graphics.setColor(color);
    }

    public void setMoveing(boolean moveing) {
        isMoveing = moveing;
    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(x, y, direciton,tankFrame));
    }


    private void move() {
//        LogUtils.debug("isMoveing = "+isMoveing);
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
