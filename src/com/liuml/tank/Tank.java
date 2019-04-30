package com.liuml.tank;

import java.awt.*;

/**
 * 坦克类
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Tank {
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();
    private int x = 100;
    private int y = 100;

    private int speed = 5;//速度
    private Direction direciton = Direction.DOWN;//方向
    public boolean isMoveing = false;//是否移动
    private TankFrame tankFrame = null;
    private int tankType;//坦克的类型 我方,敌方,友方
    private boolean living = true;//是否存活


    public Tank(int x, int y, Direction direciton, int tankType, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankType = tankType;
        this.direciton = direciton;
        this.tankFrame = tankFrame;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLiving() {
        return living;
    }


    public void setDireciton(Direction direciton) {
        this.direciton = direciton;
    }

    public void paint(Graphics graphics) {
        if (!isLiving()) {
            tankFrame.tankList.remove(this);
        }
        if (direciton == null) return;
        switch (direciton) {
            case UP:
                graphics.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case LEFT_UP:
                graphics.drawImage(ResourceMgr.tankLU, x, y, null);
                break;
            case LEFT_DOWN:

                graphics.drawImage(ResourceMgr.tankLD, x, y, null);
                break;
            case RIGHT_UP:
                graphics.drawImage(ResourceMgr.tankRU, x, y, null);
                break;
            case RIGHT_DOWN:
                graphics.drawImage(ResourceMgr.tankRD, x, y, null);
                break;
        }


        move();
//        graphics.setColor(color);
    }


    public void setMoveing(boolean moveing) {
        isMoveing = moveing;
    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(x, y, direciton, tankFrame));
    }


    private void move() {

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

    public void die() {
        living = false;
    }
}
