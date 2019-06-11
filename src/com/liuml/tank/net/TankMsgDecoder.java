package com.liuml.tank.net;

import java.util.List;
import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.TankGroup;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:16
 */
public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if(in.readableBytes()<33)return;//TCP 拆包粘包问题 只有小于33才算是一个消息
        TankJoinMsg msg = new TankJoinMsg();

        msg.x = in.readInt();
        msg.y = in.readInt();
        msg.mDirection = Direction.values()[in.readInt()];
        msg.moving = in.readBoolean();
        msg.mGroup = TankGroup.values()[in.readInt()];
        msg.mUUID = new UUID(in.readLong(), in.readLong());

        out.add(msg);
    }
}
