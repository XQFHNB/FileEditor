package com.xqf.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这个类也只是一个演示类，读取C盘的一些信息
 * Created by XQF on 2016/12/23.
 */
public class ReadC {
    public static void main(String[] args) throws Exception {
        writeFile();
    }

    private static void writeFile() throws Exception {
        //由于直接写在txt中看起来不方便，所以弄成html的table看着比较直接
        StringBuffer str = new StringBuffer("<table border='1'>");
        File file = new File("C:" + File.separator);
        File list[] = file.listFiles();
        for (int i = 0; i < list.length; i++) {// 迭代输出查看;
            String size = "<tr><td>" + list[i].getName() + "</td><td>"
                    + formetFileSize(getFileSize(list[i])) + "</td></tr>";
            str.append(size);
            System.out.println(list[i].getName() + "    "
                    + getFileSize(list[i]));
        }
        str.append("</table>");
        //方便日志记录，把文件名弄成日期
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        OutputStream os = new FileOutputStream(new File("d:" + File.separator
                + "C盘日志" + File.separator + dateStr + ".html"));
        //写入html
        os.write(str.toString().getBytes());
        os.close();
    }

    // 通过递归获取文件(夹)的大小，返回的是字节数
    private static long getFileSize(File file) {
        // 如果是文件，直接返回文件大小
        if (file.isFile())
            return file.length();
            //遍历文件夹
        else {
            long size = 0;
            File fileList[] = file.listFiles();
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    size = size + getFileSize(fileList[i]);
                }
            }
            return size;
        }
    }

    //将获取的字节进行格式化
    private static String formetFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.000");
        String fileSizeString = "";
        if (size == 0) {
            return "0B";
        } else if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "KB";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}