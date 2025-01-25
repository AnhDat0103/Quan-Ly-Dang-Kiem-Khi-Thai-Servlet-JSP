/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAT
 */
public class DBContext {

    private static DBContext instance = new DBContext();
    Connection connection;

    public static DBContext getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private DBContext() {
        try {
            if (connection == null || connection.isClosed()) {
                String username = "sa";
                String password = "0123456789";
                String url = "jdbc:sqlserver://DESKTOP-2RGPUBI\\SQLEXPRESS:1433;databaseName=PRJ03;trustServerCertificate=true";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

}
