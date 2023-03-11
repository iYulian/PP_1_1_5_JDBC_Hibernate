package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        try {
            if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
                session.beginTransaction();
            }
            session.createNativeQuery("create table if not exists User (id bigint not null auto_increment, age tinyint, " +
                    "lastName varchar(35), name varchar(35), primary key (id)) engine=MyISAM").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public void dropUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        try {
            if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
                session.beginTransaction();
            }
            session.createNativeQuery("drop table if exists user").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.getCurrentSession();
        try {
            User user = new User(name, lastName, age);
            if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
                session.beginTransaction();
            }
            session.save(String.valueOf(User.class), user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public void removeUserById(long id) {

        Session session = sessionFactory.getCurrentSession();
        try {
            if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
                session.beginTransaction();
            }
            session.createQuery("delete User where id = " + id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();
        if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
            session.beginTransaction();
        }
        List<User> list = session.createQuery("from User", User.class).getResultList();
        return list;

    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        try {
            if (!session.getTransaction().isActive()) { // нет commit'а в методе getAllUsers(), для этого везде поставил условие
                session.beginTransaction();
            }
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

    }
}
