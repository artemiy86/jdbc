package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String HOST = "jdbc:mysql://localhost:3306/new_schema?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST,USER,PASSWORD);
    }
}
