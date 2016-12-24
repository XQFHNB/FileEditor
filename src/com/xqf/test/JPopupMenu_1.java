package com.xqf.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by XQF on 2016/12/23.
 */
public class JPopupMenu_1 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 2924353502687938030L;

    public JPopupMenu_1() {
        // TODO Auto-generated constructor stub
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("剪切");
        JMenuItem menuItem2 = new JMenuItem("复制");
        JMenuItem menuItem3 = new JMenuItem("粘贴");
        JMenu menuSub = new JMenu("编辑");
        JMenuItem menuSubItem1 = new JMenuItem("只读");
        JMenuItem menuSubItem2 = new JMenuItem("可写");
        JMenu menuSubSub = new JMenu("字体");
        menuSub.add(menuSubItem1);
        menuSub.add(menuSubItem2);
        menuSub.add(menuSubSub);
        JMenuItem menuSubSubItem1 = new JMenuItem("加粗");
        JMenuItem menuSubSubItem2 = new JMenuItem("倾斜");
        menuSubSub.add(menuSubSubItem1);
        menuSubSub.add(menuSubSubItem2);

        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);
        popupMenu.add(menuSub);

        JLabel label = new JLabel("请在这里单击鼠标右键！");

        Container container = getContentPane();
        container.add(label, BorderLayout.CENTER);

        // MouseAdapter (abstract class) is different from
        // the MouseListener (interface). The interest of the abstract class
        // is that we need not to override all the methods declared in
        // the corresponding interface.
        container.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JPopupMenu_1 frame = new JPopupMenu_1();
        frame.setTitle("创建弹出式菜单");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 400, 300);
    }

}
