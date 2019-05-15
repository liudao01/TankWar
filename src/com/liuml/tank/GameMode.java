package com.liuml.tank;

import com.liuml.tank.util.RandomUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * desc 门面模式
 * Created by liuml.
 * Created time 2019/5/15.
 */
public class GameMode {

    private int x = 200;
    private int y = 200;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Tank tank;//主角坦克
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameMode() {

        tank = new Tank(x, y, Direction.DOWN, TankGroup.MYTANK, this);
    }

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        if (tank != null) {
            if (!tank.isLiving()) {
                graphics.setColor(Color.GREEN);
                graphics.drawString("你挂了-- 按下F1 复活", 300, 300);
            } else {
                tank.paint(graphics);
            }

            graphics.setColor(Color.GREEN);
            graphics.drawString("当前子弹个数" + bulletList.size(), 20, 50);
            graphics.drawString("按下回车添加敌方坦克-当前敌方坦克个数" + tankList.size(), 20, 70);
            graphics.drawString("爆炸集合 " + explodes.size(), 20, 90);
            String fireType = "普通子弹";
            if (tank.fireType == 1) {
                fireType = "普通子弹";
            } else if (tank.fireType == 2) {
                fireType = "四方子弹";
            }
            graphics.drawString("按下P 切换发射方式 当前发射方式: " + fireType, 20, 100);
            graphics.setColor(color);

            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).paint(graphics);
            }
            for (int i = 0; i < tankList.size(); i++) {
                tankList.get(i).paint(graphics);
            }
            for (int i = 0; i < explodes.size(); i++) {
                explodes.get(i).paint(graphics);
            }
            checkCollision();
        }

    }

    //碰撞检测
    private void checkCollision() {
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++) {
                if (tankList.get(j).isLiving()) {
                    //如果子弹和坦克不是同一队伍才检测
                    if (bulletList.get(i).getGroup() != tankList.get(j).getTankGroup()) {
                        bulletList.get(i).collisionWith(tankList.get(j));
                    }
                }
            }
            //如果子弹和坦克不是同一队伍才检测
            if (bulletList.get(i).getGroup() != tank.getTankGroup() && tank.isLiving()) {
                boolean b = bulletList.get(i).collisionWith(tank);
                if (b) {
                    tank.die();
                }
            }

        }
    }

    public void addEnemyTank() {
        tankList.add(new Tank(RandomUtil.getRandomHeight(), RandomUtil.getRandomHeight(), Direction.DOWN, TankGroup.Enemy, this));
    }
}
