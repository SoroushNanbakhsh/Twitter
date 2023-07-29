package com.Twitter.Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static Connection instance = null;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306";

    private static final String USER = "root";
    private static final String PASS = "";
    private java.sql.Connection conn = null;

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (JDBC_DRIVER) {
                if (instance == null) {
                    instance = new Connection();
                }
            }
        }

        return instance;
    }

    private Connection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        conn.close();
        instance = null;
    }

    public java.sql.Connection getConn() {
        return conn;
    }
}
