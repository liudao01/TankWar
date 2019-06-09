package com.liuml.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:16
 */
public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()<8)return;

        int x = in.readInt();
        int y = in.readInt();
        out.add(new TankMsg(x, y));
    }
}
