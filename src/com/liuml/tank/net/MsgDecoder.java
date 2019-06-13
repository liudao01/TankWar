package com.liuml.tank.net;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:16
 */
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

//        if(in.readableBytes()<33)return;//TCP 拆包粘包问题 只有小于33才算是一个消息
        if (in.readableBytes() < 8) return;//数据类型 和数据长度 的int 共8字节 这两个组成字节头

        in.markReaderIndex();//标记读索引位置

        //读取类型
        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();
        //判断读取的字节数组长度 如果小于读取字节头中 数据的长度
        if (in.readableBytes() < length) {
            //还原读取的索引位置
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        in.readBytes(bytes);//把获取的字节数据放入bytes

        Msg msg = null;
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMsg();

                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();

                break;
            default:
                break;

        }
        msg.parse(bytes);
        out.add(msg);//加入消息集合

    }
}
