package jm.task.core.jdbc;

import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser( "Donatello", "Betto", ( byte ) 79 );
        userService.saveUser( "Michelangelo", "Buonarroti", ( byte ) 88);
        userService.saveUser( "Leonardo", "da Vinci", ( byte ) 67);
        userService.saveUser( "Raffaello", "da Urbino", ( byte ) 37 );

        List<User> userList = userService.getAllUsers();
        userList.stream().forEach( u -> System.out.println( u ) );

        userService.createUsersTable();
        userService.dropUsersTable();
    }
}
