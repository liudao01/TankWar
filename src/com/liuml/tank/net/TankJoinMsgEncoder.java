package com.liuml.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:02
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
       // buf.writeBytes(msg.toBytes());
        //编码不是简单的转化成bytes 而是 三部分  数据类型 数据长度  和真实数据
        buf.writeInt(msg.getMsgType().ordinal());//类型下标
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);//数据长度
        buf.writeBytes(bytes);//数据
    }
}
