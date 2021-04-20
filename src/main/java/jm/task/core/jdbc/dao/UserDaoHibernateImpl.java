package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Connection conn;
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        conn = Util.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS user (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(255) NULL," +
                "  lastName VARCHAR(255) NULL," +
                "  age INT NULL," +
                "  PRIMARY KEY (id))";
        try {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.execute(query);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }
        conn.close();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        conn = Util.getConnection();
        String query = "DROP TABLE IF EXISTS user";
        try {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.execute(query);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }
        conn.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List<User> list = session.createQuery("from User").list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
