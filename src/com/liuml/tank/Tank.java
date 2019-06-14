package com.liuml.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.liuml.tank.net.TankJoinMsg;
import com.liuml.tank.util.RandomUtil;

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
    private Direction direction = Direction.DOWN;//方向
    public boolean isMoveing = false;//是否移动
    private TankFrame tankFrame = null;
    private TankGroup tankGroup;//坦克的类型 我方,敌方,友方
    private boolean living = true;//是否存活
    Timer timer;
    Rectangle rect2;

    UUID id = UUID.randomUUID();


    /**
     * 根据加入的消息创建坦克
     *
     * @param tankJoinMsg
     */
    public Tank(TankJoinMsg tankJoinMsg) {
        this.x = tankJoinMsg.x;
        this.y = tankJoinMsg.y;
        this.tankGroup = tankJoinMsg.mGroup;
        this.direction = tankJoinMsg.mDirection;
        this.id = tankJoinMsg.mUUID;
        this.isMoveing = tankJoinMsg.moving;
        initRect();
    }

    public Tank(int x, int y, Direction direciton, TankGroup tankType, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankGroup = tankType;
        this.direction = direciton;
        this.tankFrame = tankFrame;
        if (tankGroup.equals(TankGroup.Enemy)) {
            if (timer == null) {
                timer = new Timer();
            }
            timer.schedule(new TimerTaskTest(), 1000, 2000);
        }
        initRect();
    }

    private void initRect() {
        rect2 = new Rectangle(x, y, Tank.WIDTH, Tank.HEIGHT);
        rect2.x = this.x;
        rect2.y = this.y;
        rect2.width = WIDTH;
        rect2.height = HEIGHT;
    }

    public void setTankGroup(TankGroup tankGroup) {
        this.tankGroup = tankGroup;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoveing() {
        return isMoveing;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public TankGroup getTankGroup() {
        return tankGroup;
    }

    public void paint(Graphics graphics) {
        if (!isLiving()) {
            tankFrame.tanks.remove(this);
            return;
        }
        if (direction == null) return;
        //如果敌方坦克 随机发射子弹
        if (this.tankGroup.equals(TankGroup.Enemy)) {
            if (RandomUtil.getRandomForIntegerBounded(0, 10) > 8) {
//                fire();
            }
        }
        //uuid on head
        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.drawString(id.toString(), this.x, this.y - 10);
        graphics.setColor(c);
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

    public void fire() {

        int bX = 0;
        int bY = 0;
        switch (direction) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_UP:
                bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_DOWN:
                bX = this.x;
                bY = this.y + Tank.HEIGHT / 2 + Bullet.HEIGHT / 2;
                break;
            case RIGHT_UP:
                bX = this.x + Tank.WIDTH / 2 + Bullet.HEIGHT / 2;
                bY = this.y;
                break;
            case RIGHT_DOWN:
                bX = this.x + Tank.WIDTH / 2;
                bY = this.y + Tank.HEIGHT / 2;
                break;
            default:
                break;
        }
        tankFrame.bulletList.add(new Bullet(bX, bY, direction, tankGroup, tankFrame));

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
