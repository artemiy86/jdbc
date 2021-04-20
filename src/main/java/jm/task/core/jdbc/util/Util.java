package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String HOST = "jdbc:mysql://localhost:3306/new_schema?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    public static SessionFactory getSessionFactory(){
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/new_schema?useSSL=false")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","root")
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class","thread")
                .addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();
        return config.buildSessionFactory(serviceRegistry);
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST,USER,PASSWORD);
    }
}
