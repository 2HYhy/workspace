package com.me.gacl.listener;

import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * @author yunhua.he
 * @date 2017/12/20
 * 事件监听，涉及三个组件:事件源，事件对象，事件监听器
 */
public class WindowListener {

    public static void main(String [] args) {
        Frame f = new Frame();
        f.setSize(400, 400);
        f.setVisible(true);
        //注册事件监听器
        f.addWindowListener(new java.awt.event.WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Frame f = (Frame) e.getSource();
                System.out.println("======"+f+"窗体正在关闭");
                f.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                Frame f = (Frame) e.getSource();
                System.out.println("======"+f+"窗体已经关闭");
                f.dispose();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }
}
