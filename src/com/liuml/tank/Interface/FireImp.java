package com.liuml.tank.Interface;

import com.liuml.tank.Direction;
import com.liuml.tank.TankFrame;
import com.liuml.tank.TankGroup;

/**
 * @author liuml
 * @explain 具体实现开火类
 * @time 2019/5/9 15:35
 */
public interface FireImp {
     void fireImp(int x, int y, Direction direciton, TankGroup group, TankFrame tankFrame);
}
