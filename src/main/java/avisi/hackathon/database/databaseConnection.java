package avisi.hackathon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    public Connection getDatabaseConnection() throws SQLException {
        //al dbProperties = DbProperties("database.properties")
        String connectionString = "jdbc:mysql://localhost:3306/hackathon?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=TRUE";
        String user = "username";
        String password = "password";
        return DriverManager.getConnection(connectionString, user, password);
    }
}
