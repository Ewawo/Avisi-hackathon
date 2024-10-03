package avisi.hackathon.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    public Connection getDatabaseConnection() throws SQLException {

        String connectionString = "jdbc:mysql://localhost:3306/hackathon?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=TRUE";
        String user = "root";
        String password = "password";
        return DriverManager.getConnection(connectionString, user, password);
    }
}
