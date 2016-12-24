package com.xqf.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by XQF on 2016/12/23.
 */
public class JPopupMenuTest2 extends JFrame {
    public JPopupMenuTest2() {
        JButton button2 = new JButton("101");
        this.add(button2);
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupMenu(e);
            }
        });
        this.setLayout(new FlowLayout());
        this.setBounds(100, 100, 300, 300);
        this.setVisible(true);

    }

    private void showPopupMenu(MouseEvent e) {
        // 如果当前事件与右键菜单有关（单击右键），则弹出菜单
        if (e.isPopupTrigger()) {
            JPopupMenu pop = new JPopupMenu("id1");
            final String sid = ((JButton) e.getComponent()).getText();
            JMenuItem item1 = new JMenuItem("功能1");
            item1.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    System.out.println(sid);
                }
            });
            pop.add(item1);
            //e.getComponent()表示与右键菜单关联的组件，这里指button2
            pop.show(e.getComponent(), e.getX(), e.getY());
        } else {//如果是正常单击（左键）
            System.out.println("按到了");
        }
    }

    public static void main(String[] args) {
        new JPopupMenuTest();
    }

}
