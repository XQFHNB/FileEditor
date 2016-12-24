package com.xqf.test;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XQF on 2016/12/23.
 */

//向数据库插入数据的任务类
class ReadfilesInfoAndInsertIntoDbTask implements Runnable {

    Connection connection;
    PreparedStatement pstmt;
    String sqlString;


    /**
     * 向数据库插入数据
     */
    public ReadfilesInfoAndInsertIntoDbTask() {
        this.sqlString = "insert into files values(?,?,?)";
        this.connection = DBHelper.getDbHelper().getConnection();
        try {
            this.pstmt = connection.prepareStatement(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] fs = File.listRoots();
        for (int i = 0; i < fs.length-1; i++) {
            String dispalyName = fsv.getSystemDisplayName(fs[i]);
            String destinateName = getRoots(dispalyName);
            System.out.println(i + " 测试：" + destinateName);
            File file = new File(destinateName);// 指定文件目录
              method(file);
        }
        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("没读D");


    }

    //获取盘符，因为我获取的盘符是“本地磁盘(C：)”,我要拿到括号里的字符串
    private String getRoots(String str) {

        //这个正则表达式是获取括号中的数据的，
        Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
        Matcher mat = pattern.matcher(str);
        String result = null;
        if (mat.find()) {
            result = mat.group(1) + "\\";
            System.out.println(result);
        }
        return result;
    }

    //获取每一个盘符下面的所有文件
    public void method(File file) {
        File[] fs = file.listFiles();// 得到File数组

        if (fs != null) {// 判断fs是否为null
            for (File f : fs) {
                if (f.isFile()) {// 如果是文件直接输出

                    String postFix = getExtensionName(f.getName());

//
//                    System.out.println("文件名：" + f.getName() + "     "
//                            + " 文件路径:" + f.getAbsolutePath()
//                            + " 文件扩展名:" + postFix);

                    try {
                        pstmt.setString(1, f.getAbsolutePath());
                        pstmt.setString(2, f.getName());
                        pstmt.setString(3, postFix);
                        //    pstmt.execute();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    method(f);// 否则递归调用
                }
            }
        }

    }

    //获取文件的后缀名
    public String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "." + filename;
    }
}

public class MyFileSearcher extends JFrame {

    //定义一个私有的文件的绝对路径文本域对象
    private JTextField selectField;
    private JTextArea editArea;

    private JTable table;

    //定义一个私有的“保存”按钮对象
    private JButton saveBtn;

    //定义一个私有的“浏览”按钮对象
    private JButton openFileBtn;

    //定义一个私有的记录目录层次数，其初始值为0

    public MyFileSearcher() {
        new Thread(new ReadfilesInfoAndInsertIntoDbTask()).start();

        this.init();
    }

    private void init() {

        //设置标题为 Editor
        this.setTitle("Editor");

        //设置组件的大小
        this.setBounds(300, 50, 600, 650);

        /*
         * 面板的北边，即路径输入域、浏览按钮
         */

        //创建一个选择框对象
        selectField = new JTextField(40);

        //创建一个按钮对象
        openFileBtn = new JButton("Browse");

        //为刚创建的按钮添加监听事件
        openFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String path = selectField.getText();

                // 浏览目录或者文件
//                openDirOrFile(path.replaceAll("//", "\\\\"));
                //String的replaceAll()方法，是采用正则表达式规则去匹配的。
                // 参数中的“//”在java语言中被解析为“/”，而“\\\\”在java语言中被解析成“\\”，还要经正则表达式转换为“\”。
            }
        });

        //新建一个流布局，并且左对齐的面板
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //设置画板的背景颜色
        upPanel.setBackground(Color.CYAN);

        //将选择框加入画板中
        upPanel.add(selectField);

        //将按钮加入画板中
        upPanel.add(openFileBtn);

        //将面板放在北边区域
        this.add(upPanel, BorderLayout.NORTH);

        /*
         * 创建文本编辑区，并加入到整个布局的中间区域
         */
        table = getContentTable();
        JScrollPane scollPanel = new JScrollPane(table);


        this.add(scollPanel, BorderLayout.CENTER);

        /*
         * 创建保存按钮，并为按钮添加监听事件
         */
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // 保存
                saveFile();
            }
        });


        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.green);
        southPanel.add(saveBtn);
        this.add(southPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JTable getContentTable() {


        return null;
    }

    private void saveFile() {
        FileDialog fd = new FileDialog(this, "Save File");

        //设置需要保存文件的后缀
        fd.setFile("untitled.txt");

        //设置为“保存”模式
        fd.setMode(FileDialog.SAVE);
        fd.setVisible(true);
        //获取文件名
        String fileName = fd.getFile();

        //获取对话框的当前目录
        String dir = fd.getDirectory();

        //根据目录名、文件名创建一个文件，即要保存的目标文件
        File newFile = new File(dir + File.separator + fileName);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
                    newFile)));

            String str = editArea.getText();
            pw.println(str);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    public static void main(String[] args) {
        new MyFileSearcher();

    }


}
