package com.liuml.tank;

import com.liuml.tank.util.RandomUtil;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
    public boolean isMoveing = true;//是否移动
    private TankFrame tankFrame = null;
    private TankGroup tankGroup;//坦克的类型 我方,敌方,友方
    private boolean living = true;//是否存活
    Timer timer = new Timer();


    public Tank(int x, int y, Direction direciton, TankGroup tankType, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankGroup = tankType;
        this.direciton = direciton;
        this.tankFrame = tankFrame;
        if (tankGroup.equals(TankGroup.Enemy)) {
            timer.schedule(new TimerTaskTest(), 1000, 2000);
        }
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

    public TankGroup getTankGroup() {
        return tankGroup;
    }

    public void paint(Graphics graphics) {
        if (!isLiving()) {
            tankFrame.tankList.remove(this);
            return;
        }
        if (direciton == null) return;
        //如果敌方坦克 随机发射子弹
        if (this.tankGroup.equals(TankGroup.Enemy)) {
            if (RandomUtil.getRandomForIntegerBounded(0, 10) > 5) {
                fire();
            }
        }
        switch (direciton) {
            case UP:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankUp, x, y, null);
                } else {

                    graphics.drawImage(ResourceMgr.tankU, x, y, null);
                }
                break;
            case DOWN:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankDown, x, y, null);

                } else {

                    graphics.drawImage(ResourceMgr.tankD, x, y, null);
                }
                break;
            case LEFT:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankLeft, x, y, null);
                } else {
                    graphics.drawImage(ResourceMgr.tankL, x, y, null);
                }
                break;
            case RIGHT:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankRight, x, y, null);
                } else {
                    graphics.drawImage(ResourceMgr.tankR, x, y, null);
                }
                break;
            case LEFT_UP:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankLU, x, y, null);
                } else {
                    graphics.drawImage(ResourceMgr.tankLU, x, y, null);
                }
                break;
            case LEFT_DOWN:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankLD, x, y, null);
                } else {
                    graphics.drawImage(ResourceMgr.tankLD, x, y, null);
                }
                break;
            case RIGHT_UP:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankRU, x, y, null);
                } else {

                    graphics.drawImage(ResourceMgr.tankRU, x, y, null);
                }
                break;
            case RIGHT_DOWN:
                if (tankGroup == TankGroup.MYTANK) {
                    graphics.drawImage(ResourceMgr.MainTankRD, x, y, null);
                } else {
                    graphics.drawImage(ResourceMgr.tankRD, x, y, null);
                }
                break;
        }


        move();

    }

    class TimerTaskTest extends TimerTask {

        @Override
        public void run() {
            changeDircition();
        }
    }

    public void changeDircition() {
        switch (RandomUtil.getRandomForIntegerBounded(0, 7)) {
            case 0:
                direciton = Direction.UP;
                break;
            case 1:
                direciton = Direction.DOWN;
                break;
            case 2:
                direciton = Direction.LEFT;
                break;
            case 3:
                direciton = Direction.RIGHT;
                break;
            case 4:
                direciton = Direction.LEFT_UP;
                break;
            case 5:
                direciton = Direction.LEFT_DOWN;
                break;
            case 6:
                direciton = Direction.RIGHT_UP;
                break;
            case 7:
                direciton = Direction.RIGHT_DOWN;
                break;
        }
    }


    public void setMoveing(boolean moveing) {
        isMoveing = moveing;
    }

    public void fire() {

        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tankFrame.bulletList.add(new Bullet(bX, bY, direciton, tankGroup, tankFrame));
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

    /**
     * 复活
     */
    public void Resurrection() {
        living = true;
    }

    public void die() {
        if (timer != null) {
            timer.cancel();
        }
        living = false;
    }

}
