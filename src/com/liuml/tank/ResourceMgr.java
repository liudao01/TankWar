package com.liuml.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL, tankU, tankR, tankD, tankLU, tankLD, tankRU, tankRD, MainTankDown, MainTankUp, MainTankLeft, MainTankRight,
            MainTankLU,MainTankLD,MainTankRU,MainTankRD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD, bulletLU, bulletLD, bulletRU, bulletRD;
    public static BufferedImage[] explodes = new BufferedImage[16];


    static {
        try {
            System.out.println("初始化打印");
//            LogUtils.debug("getResource() = "+ResourceMgr.class.getClassLoader().getResource("images/tankL.gif").getPath());
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankLU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankLU.gif"));
            tankLD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankLD.gif"));
            tankRU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankRU.gif"));
            tankRD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankRD.gif"));

            MainTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankUp.png"));
            MainTankDown = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankDown.png"));
            MainTankLeft = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankLeft.png"));
            MainTankRight = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankRight.png"));
            MainTankLU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankLU.png"));
            MainTankLD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankLD.png"));
            MainTankRU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankRU.png"));
            MainTankRD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/MainTankRD.png"));

            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            bulletLU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileLU.gif"));
            bulletLD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileLD.gif"));
            bulletRU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileRU.gif"));
            bulletRD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileRD.gif"));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
