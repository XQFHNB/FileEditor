package com.xqf.test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**  这个类用来测试点击鼠标右键的弹出菜单的实例
 * Created by XQF on 2016/12/23.
 */
public class Demo2 {
    public static void main(String[] args) {
        final JFrame jf = new JFrame("窗体");
        jf.setSize(300, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);


        // 创建弹出菜单
        final JPopupMenu jp = new JPopupMenu();
        jp.add("红色");
        jp.add("蓝色");
        jf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // 弹出菜单
                    jp.show(jf, e.getX(), e.getY());
                }
            }
        });
        jf.setVisible(true);
    }
}
