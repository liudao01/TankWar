package com.liuml.tank.net;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:02
 */
public class TankJoinMsg {
    public int x, y;

    public TankJoinMsg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankMsg:" + x + "," + y;
    }
}
