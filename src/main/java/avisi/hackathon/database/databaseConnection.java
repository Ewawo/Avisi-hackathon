package avisi.hackathon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    public Connection getDatabaseConnection() throws SQLException {

        String connectionString = "jdbc:mysql://localhost:3306/hackathon?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=TRUE";
        String user = "root";
        String password = "admin";
        return DriverManager.getConnection(connectionString, user, password);
    }
}
