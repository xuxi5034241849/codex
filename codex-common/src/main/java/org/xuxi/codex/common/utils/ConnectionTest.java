package org.xuxi.codex.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    
    public static Connection getConnection() {
        // 定义连接
        Connection connection = null;
        
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/generator?useSSL=false", "root", "111123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    


    public static void main(String[] args) {
        getConnection();
    }
}