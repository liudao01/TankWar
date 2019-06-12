package com.liuml.tank.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.Tank;
import com.liuml.tank.TankGroup;

/**
 * @author liuml
 * @explain
 * @time 2019-06-05 15:02
 */
public class TankJoinMsg {
    public int x, y;
    public Direction mDirection;
    public boolean moving;
    public TankGroup mGroup;
    public UUID mUUID;

    public TankJoinMsg(int x, int y, Direction direction, boolean moving, TankGroup group, UUID UUID) {
        this.x = x;
        this.y = y;
        mDirection = direction;
        this.moving = moving;
        mGroup = group;
        mUUID = UUID;
    }

    public TankJoinMsg(Tank mainTank) {
        this.x = mainTank.getX();
        this.y = mainTank.getY();
        mDirection = mainTank.getDirection();
        this.moving = mainTank.isMoveing;
        mGroup = mainTank.getTankGroup();
        mUUID = mainTank.getId();

    }
    public TankJoinMsg() {

    }


    @Override
    public String toString() {
        return "TankJoinMsg{" +
            "x=" + x +
            ", y=" + y +
            ", mDirection=" + mDirection +
            ", moving=" + moving +
            ", mGroup=" + mGroup +
            ", mUUID=" + mUUID +
            '}';
    }

    //把消息转换成字节数组
    public byte[] toBytes() {

        ByteArrayOutputStream baos = null;//字节数组
        DataOutputStream dos = null;//专门写数据的
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            //使用jdk 自带的
            dos = new DataOutputStream(baos);//为什么不用netty的BytBuf  使用BytBuf 相当于绑定了netty 以后换框架不好换
            //dos.writeInt(TYPE.ordinal());
            dos.writeInt(x);//int 4个字节
            dos.writeInt(y);//4个字节
            dos.writeInt(mDirection.ordinal());//enum 的ordinal 是数组的下标. 4个字节
            dos.writeBoolean(moving);//boolean 类型 往外写 1个字节
            dos.writeInt(mGroup.ordinal());//4个字节
            //long 8个字节
            dos.writeLong(mUUID.getMostSignificantBits());//uuid 是128位  getMostSignificantBits 是高64位
            dos.writeLong(mUUID.getLeastSignificantBits());//getLeastSignificantBits 是低64位
            //dos.writeUTF(name);
            //一共33个字节
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
}
