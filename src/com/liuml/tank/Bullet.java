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
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int speed = 30;//速度
    private Direction direciton;
    private TankFrame tankFrame;
    private boolean living = true;//是否存活
    private TankGroup group;

    public Bullet(int x, int y, Direction direciton, TankGroup group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.group = group;
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
        if (!living) {
            tankFrame.bulletList.remove(this);
        }
        if (direciton == null) return;
        switch (direciton) {
            case UP:
                graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case LEFT_UP:
                graphics.drawImage(ResourceMgr.bulletLU, x, y, null);
                break;
            case LEFT_DOWN:
                graphics.drawImage(ResourceMgr.bulletLD, x, y, null);
                break;
            case RIGHT_UP:
                graphics.drawImage(ResourceMgr.bulletRU, x, y, null);
                break;
            case RIGHT_DOWN:
                graphics.drawImage(ResourceMgr.bulletRD, x, y, null);
                break;
        }
        move();
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
        //屏幕外 销毁
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }

    }


    //碰撞检测
    public boolean collisionWith(Tank tank) {

        //TODO: 用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rect1.intersects(rect2)) {
            tankFrame.explodes.add(new Explode(tank.getX(), tank.getY(), group, tankFrame));
            tank.die();
            this.die();
            return true;
        }
        return false;
    }

    private void die() {
        this.living = false;
    }
}
