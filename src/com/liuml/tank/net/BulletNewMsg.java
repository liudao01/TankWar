package com.liuml.tank.net;

import java.io.*;
import java.util.UUID;

import com.liuml.tank.Bullet;
import com.liuml.tank.Direction;
import com.liuml.tank.TankFrame;
import com.liuml.tank.TankGroup;

import static com.liuml.tank.net.MsgType.BulletNew;

/**
 * @author liuml
 * @explain 子弹创建消息
 * @time 2019-07-01 14:08
 */
public class BulletNewMsg extends Msg {
    public UUID playerID;//坦克的uuid
    public UUID id;//子弹的UUID
    public int x, y;
    public Direction dir;
    public TankGroup group;
    public BulletNewMsg(Bullet bullet) {
        this.playerID = TankFrame.INSTANCE.getMainTank().getId();
        this.id = bullet.getId();
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDireciton();
        this.group = bullet.getGroup();
    }

    public BulletNewMsg(UUID playerID, UUID id, int x, int y, Direction dir, TankGroup group) {
        this.playerID = playerID;
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public BulletNewMsg() {
    }

    @Override
    public void handle() {

    }

    @Override
    public byte[] toBytes() {
        //数据流
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            //坦克的uuid
            dos.writeLong(playerID.getMostSignificantBits());
            dos.writeLong(playerID.getLeastSignificantBits());
            //子弹的uuid
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
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
            this.playerID = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Direction.values()[dis.readInt()];
            this.group = TankGroup.values()[dis.readInt()];
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
        return BulletNew;
    }

    @Override
    public String toString() {
        return "BulletNewMsg{" +
            "playerID=" + playerID +
            ", id=" + id +
            ", x=" + x +
            ", y=" + y +
            ", dir=" + dir +
            ", group=" + group +
            '}';
    }
}
