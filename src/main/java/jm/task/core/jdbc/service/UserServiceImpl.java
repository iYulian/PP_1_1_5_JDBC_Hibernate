package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); Statement statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE IF NOT EXISTS Task3");
            statement.execute("CREATE TABLE IF NOT EXISTS `task2`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NULL,\n" +
                    "  PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); PreparedStatement prepareStatement = connection
                .prepareStatement("insert into user (name, lastname, age) values (?, ?, ?)")) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2,lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); PreparedStatement prepareStatement = connection
                .prepareStatement("delete from user where id = ?")) {
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); Statement statement = connection.createStatement()) {
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
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task2",
                "root", "springcourse"); Statement statement = connection.createStatement()) {
            statement.execute("delete from user ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
