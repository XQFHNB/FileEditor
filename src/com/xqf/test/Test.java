package com.xqf.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XQF on 2016/12/23.
 */
public class Test {
    public static void main(String[] args) {
//        String str = "<a href=\"#\" onClick=\"return open_neCw_window( '/patroninfo~S0*chx/1069163/modpinfo' )\">";
        String str = "本地磁盘(C:)";

        Pattern pat = Pattern.compile("(?<=\\()(.+?)(?=\\))");
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            System.out.println(mat.group(1));
        }
    }
}