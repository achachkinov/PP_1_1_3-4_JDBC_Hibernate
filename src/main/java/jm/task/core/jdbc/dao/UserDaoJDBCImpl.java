package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;

    public UserDaoJDBCImpl() {
        util = Util.getInstance();
    }

    @Override
    public void createUsersTable() {
        try {
            String createUserTableRequest = "CREATE TABLE IF NOT EXISTS User ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100), "
                + "last_name VARCHAR(100), "
                + "age TINYINT);";
            util.executeUpdate( createUserTableRequest );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            util.executeUpdate( "DROP TABLE IF EXISTS User;" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUserRequest = String.format( "INSERT INTO User (name, last_name, age) VALUES ('%s', '%s', %d);",
            name,
            lastName,
            age
        );
        try {
            util.executeUpdate( saveUserRequest );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUserRequest = String.format("DELETE FROM User WHERE id = %d;",
            id
        );
        try {
            util.executeUpdate( removeUserRequest );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String getAllUsersRequest = "SELECT * FROM User;";
        try {
            ResultSet rs = util.executeQuery( getAllUsersRequest );
            List<User> userList = new ArrayList<>();
            fillUserList( userList, rs );
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void fillUserList( List<User> userList, ResultSet rs ) throws SQLException {
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String lastName = rs.getString("last_name");
            byte age = rs.getByte( "age" );
            User user = new User( name, lastName, age );
            user.setId( id );
            userList.add( user );
        }
    }

    @Override
    public void cleanUsersTable() {
        String cleanUsersRequest = "TRUNCATE TABLE User;";
        try {
            util.executeUpdate( cleanUsersRequest );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
