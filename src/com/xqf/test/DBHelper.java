package com.xqf.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库的帮助类
 * Created by XQF on 2016/12/7.
 */
public class DBHelper {

    Connection connection;
    String driverString = "com.mysql.jdbc.Driver";
    String urlString = "jdbc:mysql://localhost:3306/javaDb?useSSL=false";
    String userName = "root";
    String password = "125880";

    private DBHelper() {
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(urlString, userName, password);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库获取连接失败！");
        }
    }

    public static DBHelper getDbHelper() {
        return new DBHelper();
    }


    //获取连接，这是这个类最有用的方法
    public Connection getConnection() {
        return connection;
    }


    //关闭连接，单例模式严格使用，不严格的话获取到connection就可以关闭了，这个方法没用
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库关闭失败！");
        }
    }

    public static void main(String[] args) {
        Connection connection = DBHelper.getDbHelper().getConnection();
    }
}
