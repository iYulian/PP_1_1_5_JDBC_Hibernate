package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Anton", "Haul", (byte) 85);
        userDaoHibernate.saveUser("Jorg", "Ver", (byte) 64);
        userDaoHibernate.saveUser("Li", "Cong", (byte) 48);
        userDaoHibernate.saveUser("Shi", "Cin", (byte) 25);

        userDaoHibernate.getAllUsers().forEach(System.out::println);

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

    }
}
