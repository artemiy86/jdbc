package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoHibernateImpl UserDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        UserDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoHibernate.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        UserDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return UserDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDaoHibernate.cleanUsersTable();
    }
}
