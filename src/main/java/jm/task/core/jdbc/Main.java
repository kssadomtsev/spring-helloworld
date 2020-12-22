package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (int i = 0; i < 4; i++) {
            userService.saveUser("Name" + i, "LastName", (byte) (18 + i));
            System.out.printf("User с именем – %s добавлен в базу данных\n", "Name" + i);
        }
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
