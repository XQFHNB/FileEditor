package com.xqf.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XQF on 2016/12/23.
 */
public class Test1 {
    public void test1() {
        // 正则表达式“.+/(.+)$”的含义就是：被匹配的字符串以任意字符序列开始，后边紧跟着字符“/”，
        // 最后以任意字符序列结尾，“()”代表分组操作，这里就是把文件名做为分组，匹配完毕我们就可以通过Matcher
        // 类的group方法取到我们所定义的分组了。需要注意的这里的分组的索引值是从1开始的，所以取第一个分组的方法是m.group(1)而不是m.group(0)。
//        String regEx = ".+/(.+)$";
        String regEx = "(?<=.)";

        String str = "c:/test1/test1/文件.pdf";
        // String regEx = ".+\\\\(.+)$";
        // String str = "c:\\dir1\\dir2\\文件.pdf";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (!m.find()) {
            System.out.println("文件路径格式错误!");
            return;
        }
        System.out.println(m.group(1));

    }

    public String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
//        t1.test1();
        System.out.println(t1.getExtensionName("c:/test1/test1/文件.pdf"));
    }
}
