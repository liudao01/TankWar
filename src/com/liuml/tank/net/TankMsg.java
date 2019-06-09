package com.liuml.tank.net;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:02
 */
public class TankMsg {
    public int x, y;

    public TankMsg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankMsg:" + x + "," + y;
    }
}
