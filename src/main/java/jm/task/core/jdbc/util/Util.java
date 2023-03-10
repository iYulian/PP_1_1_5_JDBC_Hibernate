package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {
        try {
            return  DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                    "root", "springcourse");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        Configuration configuration = new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Task2?useSSL=false")
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.username", "root").setProperty("hibernate.connection.password", "springcourse")
                .setProperty("hibernate.hbm2ddl.auto", "update").setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect").setProperty("hibernate.show_sql", "true")
                .addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
