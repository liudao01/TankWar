package com.liuml.tank;


import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import com.liuml.tank.net.Client;
import com.liuml.tank.net.TankStartMovingMsg;
import com.liuml.tank.net.TankStopMsg;
import com.liuml.tank.util.LogUtils;
import com.liuml.tank.util.RandomUtil;

public class TankFrame extends Frame {

    private int x = 200;
    private int y = 200;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    List<Bullet> bulletList = new ArrayList<>();
    Map<UUID, Tank> tanks = new HashMap<>();
    List<Explode> explodes = new ArrayList<>();
    public static final TankFrame INSTANCE = new TankFrame();

    Tank tank;

    //主角坦克
    private TankFrame() {
        tank = new Tank(RandomUtil.getRandomWidth(), RandomUtil.getRandomHeight(), Direction.DOWN,
            TankGroup.MYTANK, this);
        // TODO Auto-generated constructor stub
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        this.addKeyListener(new MykeyListener());


    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        //双缓冲
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        if (tank != null) {
            if (!tank.isLiving()) {
                graphics.setColor(Color.GREEN);
                graphics.drawString("你挂了-- 按下F1 复活", 300, 300);
            } else {
                tank.paint(graphics);
            }

            graphics.setColor(Color.GREEN);
            graphics.drawString("当前子弹个数" + bulletList.size(), 20, 50);
            graphics.drawString("当前敌方坦克个数" + tanks.size(), 20, 70);
            graphics.drawString("爆炸集合 " + explodes.size(), 20, 90);
            graphics.setColor(color);

            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).paint(graphics);
            }

            //java8 stream
            tanks.values().stream().forEach((e) -> e.paint(graphics));

            for (int i = 0; i < explodes.size(); i++) {
                explodes.get(i).paint(graphics);
            }
//            checkCollision();
            Collection<Tank> values = tanks.values();
            for (int i = 0; i < bulletList.size(); i++) {
                for (Tank t : values) {
                    bulletList.get(i).collisionWith(t);
                }
            }
        }


    }

    /**
     * 添加坦克
     *
     * @param t
     */
    public void addTank(Tank t) {
        LogUtils.debug("坦克= " + t.toString());
        tanks.put(t.getId(), t);
//        LogUtils.debug("坦克= "+t.toString());

    }

    public Tank findTankByUUID(UUID id) {

        return tanks.get(id);
    }

    //碰撞检测
//    private void checkCollision() {
//        for (int i = 0; i < bulletList.size(); i++) {
//
//            for (int j = 0; j < tanks.size(); j++) {
//                System.out.println("tanks size= " + tanks.size());
//                System.out.println("j = " + j);
//                System.out.println("tanks.get(j) = " + tanks.get(j));
//                if (tanks.get(j) != null && tanks.get(j).isLiving()) {
//                    bulletList.get(i).collisionWith(tanks.get(j));
//                }
//            }
//
//            if (tank.isLiving()) {
//                bulletList.get(i).collisionWith(tank);
//            }
//
//        }
//    }

    public void addEnemyTank() {
//        tanks.add(new Tank(RandomUtil.getRandomHeight(), RandomUtil.getRandomHeight(), Direction.DOWN,
//            TankGroup.Enemy, this));
    }

    public void addBullte(Bullet bullet) {
        bulletList.add(bullet);
    }

    class MykeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_ENTER:
                    addEnemyTank();
                    break;
                case KeyEvent.VK_F1:
                    tank.Resurrection();
                    break;
                default:
                    break;
            }
            getDireciton();

        }


        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            getDireciton();

        }


        //根据方向 处理坦克的坐标
        private void getDireciton() {
            if (!bL && !bD && !bR && !bU) {
                tank.setMoveing(false);
                //发送停止消息
                System.out.println("停止");
                Client.INSTANCE.sendMsg(new TankStopMsg(getMainTank()));
            } else {

                tank.setMoveing(true);
            }
            if (bL) {
                tank.setDirection(Direction.LEFT);
            }
            if (bU) {
                tank.setDirection(Direction.UP);
            }
            if (bR) {
                tank.setDirection(Direction.RIGHT);
            }
            if (bD) {
                tank.setDirection(Direction.DOWN);
            }
            if (bL && bU) {
                tank.setDirection(Direction.LEFT_UP);
            }
            if (bL && bD) {
                tank.setDirection(Direction.LEFT_DOWN);
            }
            if (bR && bU) {
                tank.setDirection(Direction.RIGHT_UP);
            }
            if (bR && bD) {
                tank.setDirection(Direction.RIGHT_DOWN);
            }
            //判断如果坦克是正在移动
            //发送坦克移动的消息
            System.out.println("当前信息 tank.isMoveing() " + tank.isMoveing());
            if (tank.isMoveing()) {
                Client.INSTANCE.sendMsg(new TankStartMovingMsg(getMainTank()));
            }
        }


    }

    public Tank getMainTank() {
        return this.tank;
    }

}
