//package com.xqf.test;
//
//import java.io.File;
//
///**
// * Created by XQF on 2016/12/23.
// */
//public class Demo {
//    public static void main(String[] args) {
//        File file = new File("C:\\");// 指定文件目录
//        method(file);
//    }
//
//    public static void method(File file) {
//        File[] fs = file.listFiles();// 得到File数组
//
//        if (fs != null) {// 判断fs是否为null
//            for (File f : fs) {
//                if (f.isFile()) {// 如果是文件直接输出
//                    System.out.println(f.getName() + "     " + f.getAbsolutePath());
//                } else {
//                    method(f);// 否则递归调用
//                }
//            }
//        }
//
//    }
//}
