package com.liuml.tank;

import com.liuml.tank.Interface.FireImp;

/**
 * @author liuml
 * @explain 四个方向的子弹
 * @time 2019/5/9 15:37
 */
public class FourFire implements FireImp {


    @Override
    public void fireImp(Tank tank) {
        int bX = 0;
        int bY = 0;
        Direction[] dirs = Direction.values();
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
        for (Direction direction : dirs) {
            new Bullet(bX, bY, direction, tank.getTankGroup(), tank.gameMode);
        }
    }
}
