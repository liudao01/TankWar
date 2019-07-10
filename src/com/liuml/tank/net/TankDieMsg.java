package com.liuml.tank.net;

import java.io.*;
import java.util.UUID;

import com.liuml.tank.Tank;
import com.liuml.tank.TankFrame;

/**
 * @author liuml
 * @explain
 * @time 2019-07-10 10:00
 */
public class TankDieMsg extends Msg {

    private UUID bulletId;//谁杀死我的坦克
    private UUID id;//坦克ID

    public TankDieMsg() {
    }

    public TankDieMsg(UUID bulletId, UUID id) {
        this.bulletId = bulletId;
        this.id = id;
    }

    @Override
    public void handle() {
        System.out.println("we got a tank die:" + id);
        System.out.println("and my tank is:" + TankFrame.INSTANCE.getMainTank().getId());
        //根据坦克的ID 找到坦克
//        Tank tt = TankFrame.INSTANCE.findTankByUUID(id);

        //根据子弹的id 找到子弹

        //如果坦克的id和子弹射中的坦克id 相同 让自己的坦克死亡
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId())) {
            TankFrame.INSTANCE.getMainTank().die();
        }else {
            //找到对方的坦克 让他死亡
            Tank t = TankFrame.INSTANCE.findTankByUUID(id);
            if (t != null) {
                t.die();
            }
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
            dos.writeLong(bulletId.getMostSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.bulletId = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(), dis.readLong());
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
        return MsgType.TankDie;
    }

    @Override
    public String toString() {
        return "TankDieMsg{" +
            "bulletId=" + bulletId +
            ", id=" + id +
            '}';
    }
}
