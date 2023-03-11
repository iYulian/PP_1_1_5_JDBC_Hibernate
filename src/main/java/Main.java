import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Anton", "Haul", (byte) 85);
        userService.saveUser("Jorg", "Ver", (byte) 64);
        userService.saveUser("Li", "Cong", (byte) 48);
        userService.saveUser("Shi", "Cin", (byte) 25);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}