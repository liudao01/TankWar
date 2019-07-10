package com.liuml.tank.net;

import java.io.*;
import java.util.UUID;

import com.liuml.tank.Tank;
import com.liuml.tank.TankFrame;

/**
 * desc
 * Created by liuml.
 * Created time 2019/6/13.
 */
public class TankStopMsg extends Msg {

    public static final MsgType TYPE = MsgType.TankStop;
    UUID id;
    int x, y;

    public TankStopMsg() {
    }

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg(Tank mainTank) {
        this.id = mainTank.getId();
        this.x = mainTank.getX();
        this.y = mainTank.getY();
    }

    @Override
    public void handle() {
        Tank tank = TankFrame.INSTANCE.findTankByUUID(this.id);
        if (tank != null) {
            tank.setMoveing(false);
            tank.setX(this.x);
            tank.setY(this.y);

        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos  = null;
        byte[] bytes= null;
        try {

            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
//            dos.writeInt(TYPE.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream((new ByteArrayInputStream(bytes)));
        try {
            //略过消息类型
//            dis.readInt();
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }
}




















