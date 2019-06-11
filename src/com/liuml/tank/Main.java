package com.liuml.tank;

import com.liuml.tank.net.Client;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        com.liuml.tank.TankFrame tankFrame = new com.liuml.tank.TankFrame();
        int initTankCount = com.liuml.tank.PropertyMgr.getInstance().getInt("initTankCount");
//        for (int i = 0; i < initTankCount; i++) {
//            tankFrame.addEnemyTank();
//        }

        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();
        //连接到服务器
        Client c = new Client();
        c.connect();
    }



}
