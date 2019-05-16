package com.liuml.tank;

import java.awt.*;

/**
 * desc 所有游戏物体的基类
 * Created by liuml.
 * Created time 2019/5/15.
 */
public abstract class GameObject {
    int x,y;

    public abstract void paint(Graphics graphics);
    public abstract void getHeight();
    public abstract void getwidth();

}
