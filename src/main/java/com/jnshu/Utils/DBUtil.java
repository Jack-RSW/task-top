package com.jnshu.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    // 工具类，私有化无参构造函数
    public DBUtil() {
    }

    //静态变量，供全局操作且用于静态代码块加载资源。
    private static String url = null;
    private static String driverClass = null;
    private static String userName = null;
    private static String passWord = null;

    //静态代码块，随着类的加载而执行，而且只执行一次
    static {
        Properties properties = new Properties();

        InputStream resourceAsStream = DBUtil.class.getResourceAsStream("/db.properties");

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("jdbc_url");
        driverClass = properties.getProperty("jdbc_driverClass");
        userName = properties.getProperty("jdbc_user");
        passWord = properties.getProperty("jdbc_password");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //通过getConnection()可以获得一个连接对象
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,userName,passWord);
        return conn;
    }

}
