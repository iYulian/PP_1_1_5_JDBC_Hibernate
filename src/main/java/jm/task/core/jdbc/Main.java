package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ivan", "Petrov", (byte) 120);
        userService.saveUser("Jilly", "Ivanov", (byte) 54);
        userService.saveUser("Nose", "Jose", (byte) 36);
        userService.saveUser("Klement", "Kally", (byte) 18);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
