package com.liuml.tank.net;

/**
 * @author liuml
 * @explain
 * @time 2019-06-12 15:30
 */
public abstract class Msg {
    public abstract void handle();
    public abstract byte[] toBytes();
}
