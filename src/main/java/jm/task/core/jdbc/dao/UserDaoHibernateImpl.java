package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.getCurrentSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User " + "where id = " + id).executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return list;

    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User ").executeUpdate();
        session.getTransaction().commit();

    }
}
