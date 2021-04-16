package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection conn;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        conn = Util.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS user (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(255) NULL," +
                "  lastName VARCHAR(255) NULL," +
                "  age INT NULL," +
                "  PRIMARY KEY (id))";
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        conn.close();
    }

    public void dropUsersTable() throws SQLException{
        conn = Util.getConnection();
        String query = "DROP TABLE IF EXISTS user";
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        conn.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException{
        conn = Util.getConnection();
        String query = "INSERT INTO user (name, lastName, age)" +
                "VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, lastName);
        pstmt.setByte(3, age);
        pstmt.executeUpdate();
        conn.close();
        System.out.println("User c именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        conn = Util.getConnection();
        String query = "DELETE FROM user WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setLong(1, id);
        pstmt.executeUpdate();
        conn.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        conn = Util.getConnection();
        String query = "SELECT id,name,lastName,age FROM user";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while(result.next()){
            User user = new User(
                    result.getString("name"),
                    result.getString("lastName"),
                    result.getByte("age")
            );
            user.setId(result.getLong("id"));
            list.add(user);
        }
        conn.close();
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        conn = Util.getConnection();
        String query = "DELETE FROM user";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        conn.close();
    }
}
