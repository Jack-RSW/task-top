package com.jnshu.Utils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {
    //创建连接池
    static LinkedList<Connection> pool = new LinkedList<>();
    //初始化连接
    static {
        try {
            for (int i = 0; i < 5; i++) {
                pool.add(DBUtil.getConnection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //定义一个方法，从池子中获取connection，从头部获取
    public Connection getConnectionFromPool() throws SQLException {
        if (pool.size() > 0) {
            //池子中有connection
            return pool.removeFirst();
        }else {
            //如果池子中没有connection，则先加入等待队列，等待队列满了的话就新建connection(新建的
            // connection是不需要放回池子的，用完后直接销毁)
            return DBUtil.getConnection();
        }
    }

    //定义一个方法，将connection放回池子中(如果是新创建的connection则直接销毁)
    public void addBack(Connection connection){
        //pool.addLast(connection);//免不了会将新建的connection添加进池子;
        try {
            //写一个自己的connection，然后重写close()方法，通过close()方法来添加进池子。
            connection.close();//免不了会将池子中的connection销毁
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //返回pool里面连接的个数
    public int getCount(){
        return pool.size();
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (pool.size() > 0) {
            //池子中有connection
            return pool.removeFirst();
        }else {
            //如果池子中没有connection，则先加入等待队列，等待队列满了的话就新建connection(新建的
            // connection是不需要放回池子的，用完后直接销毁)
            return DBUtil.getConnection();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}