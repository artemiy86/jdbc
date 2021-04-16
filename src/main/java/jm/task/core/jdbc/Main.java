package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov",(byte)34);
        userService.saveUser("Petr","Petrov",(byte)18);
        userService.saveUser("Fedor","Fedorov",(byte)45);
        userService.saveUser("Vasya","Pupkin",(byte)22);
        //userService.removeUserById(3);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
