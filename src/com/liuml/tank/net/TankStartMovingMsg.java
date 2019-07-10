package com.liuml.tank.net;

import java.io.*;
import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.Tank;
import com.liuml.tank.TankFrame;

/**
 * @author liuml
 * @explain 坦克开始移动消息
 * @time 2019-06-13 16:18
 */
public class TankStartMovingMsg extends Msg {
    public UUID mUUID;
    public int x, y;
    public Direction mDirection;

    public TankStartMovingMsg(Tank tank) {
        mUUID = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
        mDirection = tank.getDirection();
    }

    public TankStartMovingMsg() {
    }

    public TankStartMovingMsg(UUID UUID, int x, int y, Direction direction) {
        mUUID = UUID;
        this.x = x;
        this.y = y;
        mDirection = direction;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    @Override
    public String toString() {
        return "TankStartMovingMsg{" +
            "mUUID=" + mUUID +
            ", x=" + x +
            ", y=" + y +
            ", mDirection=" + mDirection +
            '}';
    }

    @Override
    public void handle() {
        //客户端接收到消息
        //如果接收到的消息的uuid 是自己发的 则不处理
        if (this.mUUID.equals(TankFrame.INSTANCE.getMainTank().getId())) return;

        Tank tank = TankFrame.INSTANCE.findTankByUUID(this.mUUID);//根据uuid 找到坦克
        if (tank != null) {
            tank.setMoveing(true);
            tank.setX(this.x);
            tank.setY(this.y);
            tank.setDirection(this.mDirection);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(mUUID.getMostSignificantBits());
            dos.writeLong(mUUID.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(mDirection.ordinal());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
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

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.mUUID = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.mDirection = Direction.values()[dis.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }
}

