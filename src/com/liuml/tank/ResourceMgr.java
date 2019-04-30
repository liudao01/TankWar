package com.liuml.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
	public static BufferedImage tankL, tankU, tankR, tankD,tankLU,tankLD,tankRU,tankRD;
	public static BufferedImage bulletL, bulletU, bulletR, bulletD,bulletLU,bulletLD,bulletRU,bulletRD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	
 	
	static {
		try {
			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			tankLU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankLU.gif"));
			tankLD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankLD.gif"));
			tankRU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankRU.gif"));
			tankRD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankRD.gif"));

			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

			bulletLU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileLU.gif"));
			bulletLD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileLD.gif"));
			bulletRU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileRU.gif"));
			bulletRD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileRD.gif"));

			for(int i=0; i<16; i++) {
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
