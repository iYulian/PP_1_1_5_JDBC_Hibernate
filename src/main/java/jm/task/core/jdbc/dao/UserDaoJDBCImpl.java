package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.commit();
            statement.execute("CREATE DATABASE IF NOT EXISTS Task3");
            statement.execute("CREATE TABLE IF NOT EXISTS `task2`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT NULL,\n" +
                    "  PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.commit();
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepareStatement = connection
                .prepareStatement("insert into user (name, lastname, age) values (?, ?, ?)")) {
            connection.commit();
            prepareStatement.setString(1, name);
            prepareStatement.setString(2,lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepareStatement = connection
                .prepareStatement("delete from user where id = ?")) {
            connection.commit();
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            List <User> list = new LinkedList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.commit();
            statement.execute("delete from user ");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}