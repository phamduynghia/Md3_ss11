
package com.ra.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnect(){
        final String URL = "jdbc:mysql://localhost:3307/ngay1thang10nam2024";
        final String USERNAME = "root" ;
        final String PASSWORD = "12345678";
        Connection connection = null ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection ;
    }
}
