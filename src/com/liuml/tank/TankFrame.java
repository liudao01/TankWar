package com.liuml.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200;
    int y = 200;

    public TankFrame() {
        // TODO Auto-generated constructor stub
        setSize(800, 600);
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


    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
    }

    class MykeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            x = x + 30;
            y = y + 30;
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
