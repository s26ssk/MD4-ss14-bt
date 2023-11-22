package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";
    private  static  final String URL = "jdbc:mysql://localhost:3306/userLogin";
    private  static  final  String USER = "root";
    private  static  final  String PASSWORD = "123456";
    public static Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName(DRIVER_JDBC);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException ec) {
            throw new RuntimeException(ec);
        }
        return  connection;
    }

    public static  void  closeConnection(Connection connection) throws SQLException {

        if(connection != null){
            connection.close();
        }
    }
}
