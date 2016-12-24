package com.xqf.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by XQF on 2016/12/23.
 */
public class ImageFrame extends JFrame {
    JPanel jp = (JPanel) this.getContentPane();
    private JTable[] jt;
    private JButton[] jb;
    private JButton[] jb2;

    public ImageFrame() {
        try {
            this.setTitle("Click");
            jp.setLayout(null);
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void jbInit() throws Exception {
        jt = new JTable[20];
        jb = new JButton[20];
        jb2 = new JButton[20];
        for (int i = 0; i < jt.length; i++) {
            jt[i] = new JTable();
            jb[i] = new JButton("jb" + (i + 1));
            jb2[i] = new JButton("JB" + (i + 1));
        }
        int k = 0, j = 0;
        for (int i = 0; i < jt.length; i++, j++) {
            final int g = i;
            if (j % 6 == 0) {
                k++;
            }
            int x = Math.abs(j % 6);
            int marginwidth = x * 5 + 100 * x;
            int marginheight = (k - 1) * 5 + 100 * (k - 1);
            jt[i].setBounds(new Rectangle(marginwidth, marginheight, 100, 100));
            jb[i].setBounds(new Rectangle(10, 10, 80, 20));
            jb2[i].setBounds(new Rectangle(10, 40, 80, 20));
            jb2[i].setBackground(Color.white);//设置背景
            jb2[i].setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));
            //设置边框
            jt[i].add(jb[i]);
            jt[i].add(jb2[i]);
            jp.add(jt[i]);
            jb[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("点击了Button---" + g);//根据下标
                }
            });
            jb2[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("点击了Button2===" + g);//根据下标
                }
            });
        }
    }

    public static void main(String[] args) {
        ImageFrame frame = new ImageFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}