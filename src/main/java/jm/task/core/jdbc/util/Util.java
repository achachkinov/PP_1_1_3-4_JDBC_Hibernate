package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static volatile Util instance;
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Statement statement;

    private Util() {
        try {
            Connection connection = DriverManager.getConnection( URL, USER, PASSWORD );
            statement = connection.createStatement();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void execute( String command ) throws SQLException {
        statement.executeUpdate( command );
    }
	
    public static Util getInstance() {
        Util localInstance = instance;
        if (localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Util();
                }
            }
        }
        return localInstance;
    }
}
