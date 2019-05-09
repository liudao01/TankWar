package com.liuml.tank;

import com.liuml.tank.Interface.FireImp;

/**
 * @author liuml
 * @explain
 * @time 2019/5/9 15:37
 */
public class NormalFire implements FireImp {

    @Override
    public void fireImp(int x, int y, Direction direciton, TankGroup group, TankFrame tankFrame) {
        int bX = 0;
        int bY = 0;
        switch (direciton) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                bX = x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_UP:
                bX = x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_DOWN:
                bX = x;
                bY = y + Tank.HEIGHT / 2 + Bullet.HEIGHT / 2;
                break;
            case RIGHT_UP:
                bX = x + Tank.WIDTH / 2 + Bullet.HEIGHT / 2;
                bY = y;
                break;
            case RIGHT_DOWN:
                bX = x + Tank.WIDTH / 2;
                bY = y + Tank.HEIGHT / 2;
                break;
            default:
                break;
        }
        tankFrame.bulletList.add(new Bullet(bX, bY, direciton, group, tankFrame));
    }
}
