package com.liuml.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        TankFrame tankFrame = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
