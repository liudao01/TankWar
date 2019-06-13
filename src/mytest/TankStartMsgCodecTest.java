package mytest;

import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.net.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * desc
 * Created by liuml.
 * Created time 2019/4/30.
 */
public class TankStartMsgCodecTest {


    @Test
    public void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id,5, 10, Direction.DOWN);
        ch.pipeline()
            .addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankStartMoving, msgType);

        int length = buf.readInt();
        assertEquals(28, length);//消息长度是否是33

        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Direction dir = Direction.values()[dirOrdinal];

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Direction.DOWN, dir);
        assertEquals(id, uuid);
    }

    @Test
    public void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id,5, 10, Direction.DOWN);
        ch.pipeline()
            //解码
            .addLast(new MsgDecoder());

        ch.writeOutbound(msg);

        ByteBuf buf = Unpooled.buffer();
//        buf.writeBytes(msg.toBytes());
        buf.writeInt(MsgType.TankStartMoving.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankStartMovingMsg msgR = (TankStartMovingMsg)ch.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Direction.DOWN, msgR.mDirection);
        assertEquals(id, msgR.mUUID);
    }


}
