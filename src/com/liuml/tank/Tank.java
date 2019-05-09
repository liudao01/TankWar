package com.liuml.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import com.liuml.tank.Interface.FireAble;
import com.liuml.tank.util.RandomUtil;

/**
 * 坦克类
 * Created by liuml.
 * Created time 2019/4/28.
 */
public class Tank implements FireAble {

    NormalFire mNormalFire = new NormalFire();
    FourFire mFourFire = new FourFire();

    public int fireType = 1;//发射种类 1 普通 2 四个方向发射
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();
    private int x = 100;
    private int y = 100;

    private int speed = 5;//速度
    private Direction direction = Direction.DOWN;//方向
    public boolean isMoveing = true;//是否移动
    private TankFrame tankFrame = null;
    private TankGroup tankGroup;//坦克的类型 我方,敌方,友方
    private boolean living = true;//是否存活
    Timer timer = new Timer();
    Rectangle rect2;

    public Tank(int x, int y, Direction direciton, TankGroup tankType, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankGroup = tankType;
        this.direction = direciton;
        this.tankFrame = tankFrame;
        if (tankGroup.equals(TankGroup.Enemy)) {
            timer.schedule(new TimerTaskTest(), 1000, 2000);
        }
        rect2 = new Rectangle(x, y, Tank.WIDTH, Tank.HEIGHT);
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


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TankGroup getTankGroup() {
        return tankGroup;
    }

    public void paint(Graphics graphics) {
        if (!isLiving()) {
            tankFrame.tankList.remove(this);
            return;
        }
        if (direction == null) return;
        //如果敌方坦克 随机发射子弹
        if (this.tankGroup.equals(TankGroup.Enemy)) {
            if (RandomUtil.getRandomForIntegerBounded(0, 10) > 8) {
                fire();
            }
        }
        Color color = graphics.getColor();
        if (this.tankGroup.equals(TankGroup.MYTANK)) {
            graphics.setColor(Color.red);
            graphics.drawString("主角", x + 20, y);
        }
        graphics.setColor(color);

        switch (direction) {
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

    @Override
    public void fire() {

        //默认子弹
        if (fireType == 1) {
            mNormalFire.fireImp(this.x, this.y, direction, tankGroup, tankFrame);
        } else if (fireType == 2) {
            mFourFire.fireImp(this.x, this.y, direction, tankGroup, tankFrame);
        }
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
                direction = Direction.UP;
                break;
            case 1:
                direction = Direction.DOWN;
                break;
            case 2:
                direction = Direction.LEFT;
                break;
            case 3:
                direction = Direction.RIGHT;
                break;
            case 4:
                direction = Direction.LEFT_UP;
                break;
            case 5:
                direction = Direction.LEFT_DOWN;
                break;
            case 6:
                direction = Direction.RIGHT_UP;
                break;
            case 7:
                direction = Direction.RIGHT_DOWN;
                break;
        }
    }


    public void setMoveing(boolean moveing) {
        isMoveing = moveing;
    }


    private void move() {

        if (!isMoveing) {
            return;
        }
        if (direction != null) {
            switch (direction) {
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
        if (x > TankFrame.GAME_WIDTH - 50) {
            x = TankFrame.GAME_WIDTH - 50;
        }
        if (y < 20) {
            y = 20;
        }
        if (y > TankFrame.GAME_HEIGHT - 50) {
            y = TankFrame.GAME_HEIGHT - 50;
        }
        rect2.x = this.x;
        rect2.y = this.y;

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
