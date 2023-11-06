package com.codewithbubblu.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String connectionUrl="jdbc:mysql://localhost:3306/demo";
    private static final String username="root";
    private static final String password="root";
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(connectionUrl,username,password);
            System.out.println("Connection "+!connection.isClosed());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return connection;
    }
}
