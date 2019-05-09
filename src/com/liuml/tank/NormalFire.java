package com.liuml.tank;

import com.liuml.tank.Interface.FireImp;

/**
 * @author liuml
 * @explain
 * @time 2019/5/9 15:37
 */
public class NormalFire implements FireImp {


    @Override
    public void fireImp(Tank tank) {
        int bX = 0;
        int bY = 0;
        switch (tank.direction) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_UP:
                bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case LEFT_DOWN:
                bX = tank.x;
                bY = tank.y + Tank.HEIGHT / 2 + Bullet.HEIGHT / 2;
                break;
            case RIGHT_UP:
                bX = tank.x + Tank.WIDTH / 2 + Bullet.HEIGHT / 2;
                bY = tank.y;
                break;
            case RIGHT_DOWN:
                bX = tank.x + Tank.WIDTH / 2;
                bY = tank.y + Tank.HEIGHT / 2;
                break;
            default:
                break;
        }
        new Bullet(bX, bY, tank.direction, tank.getTankGroup(), tank.tankFrame);
    }
}
