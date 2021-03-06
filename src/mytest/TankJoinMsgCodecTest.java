package mytest;

import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.TankGroup;
import com.liuml.tank.net.MsgType;
import com.liuml.tank.net.TankJoinMsg;
import com.liuml.tank.net.MsgDecoder;
import com.liuml.tank.net.MsgEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author liuml
 * @explain
 * @time 2019-06-11 14:50
 */
public class TankJoinMsgCodecTest {

    @Test
    public void testEncoder() {
        //EmbeddedChannel 嵌入式的channel 不用每次启动server 验证  这个channel 虚拟连到网上
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Direction.DOWN, true, TankGroup.Enemy, id);
        ch.pipeline()
            .addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankJoin, msgType);

        int length = buf.readInt();
        assertEquals(33, length);//消息长度是否是33

        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Direction dir = Direction.values()[dirOrdinal];
        boolean moving = buf.readBoolean();
        int groupOrdinal = buf.readInt();
        TankGroup g = TankGroup.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Direction.DOWN, dir);
        assertEquals(true, moving);
        assertEquals(TankGroup.Enemy, g);
        assertEquals(id, uuid);
    }

    @Test
    public void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Direction.DOWN, true, TankGroup.Enemy, id);
        ch.pipeline()
        //解码
            .addLast(new MsgDecoder());

        ch.writeOutbound(msg);

        ByteBuf buf = Unpooled.buffer();
//        buf.writeBytes(msg.toBytes());
        buf.writeInt(MsgType.TankJoin.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankJoinMsg msgR = (TankJoinMsg)ch.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Direction.DOWN, msgR.mDirection);
        assertEquals(true, msgR.moving);
        assertEquals(TankGroup.Enemy, msgR.mGroup);
        assertEquals(id, msgR.mUUID);

    }


}
