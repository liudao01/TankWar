package com.liuml.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = PropertyMgr.getInstance().getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.addEnemyTank();
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
