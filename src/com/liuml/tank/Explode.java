package com.liuml.tank;

import java.awt.*;

/**
 * 爆炸
 * Created by liuml.
 * Created time 2019/5/1.
 */
public class Explode {

    private int x = 100;
    private int y = 100;
    private TankFrame tankFrame;
    private TankGroup group;

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int step = 0;

    public Explode(int x, int y, TankGroup group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.tankFrame = tankFrame;
    }


    public void paint(Graphics graphics) {
        if (step >= ResourceMgr.explodes.length) {
            die();
            return;
        }
        graphics.drawImage(ResourceMgr.explodes[step++], x, y, null);
    }


    //碰撞检测

    private void die() {
        tankFrame.explodes.remove(this);
    }
}
