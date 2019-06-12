package com.liuml.tank.net;

/**
 * @author liuml
 * @explain
 * @time 2019-06-12 15:30
 */
public abstract class Msg {
    public abstract void handle();//消息处理
    public abstract byte[] toBytes();//消息转换字节数组

    public abstract void parse(byte[] bytes);//消息解析
    public abstract MsgType getMsgType();//获取消息类型

}
