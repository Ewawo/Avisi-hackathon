package avisi.hackathon.authenticate;

import avisi.hackathon.database.DaoUtils;
import avisi.hackathon.database.DatabaseConnection;
import avisi.hackathon.dtos.LoginResponseDto;
import avisi.hackathon.exceptions.NotFoundException;
import avisi.hackathon.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;

@Repository
public class AuthenticationDao {

    private final DatabaseConnection databaseConnection;

    @Autowired
    public AuthenticationDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public String findPassword(String email) {
        String sql = "SELECT wachtwoord FROM User WHERE email = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("wachtwoord");
            } else {
                throw new UnauthorizedException("User not found");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        } finally {
            DaoUtils.closeResources(resultSet, statement, connection);
        }
    }


    public boolean tokenExists(String token) {
        String sql = "SELECT COUNT(*) FROM UserSession WHERE sessionId = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        } finally {
            DaoUtils.closeResources(resultSet, statement, connection);
        }
    }



    public void destroySession(String token) {
        System.out.println("token: " + token);
        String sql = "DELETE FROM UserSession WHERE sessionId = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get a connection to the database
            connection = databaseConnection.getDatabaseConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);  // Set the token in the query

            int rowsAffected = statement.executeUpdate(); // Execute the delete operation

            if (rowsAffected == 0) {
                // If no rows were affected, it means the token was not found
                throw new NotFoundException("Session not found for the provided token");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        } finally {
            // Close resources to avoid memory leaks
            DaoUtils.closeResources(statement, connection);
        }
    }

    public String getUser(String token) {
        String sql = "SELECT r.name FROM UserSession s join User u on s.userId = u.userId join Role r on u.roleId = r.roleId WHERE s.sessionId = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                var id = resultSet.getString("name");
                System.out.println(id);
                return id;
            } else {
                throw new NotFoundException("User not found for the provided token");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        }
    }


    public void insertToken(String email, String token) {
        String sqlInsertToken = "INSERT INTO UserSession (sessionId, userId) VALUES (?, ?)";
        String sqlFindUserId = "SELECT userId FROM User WHERE email = ?";
        Connection connection = null;
        PreparedStatement insertTokenStatement = null;
        PreparedStatement findUserIdStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            findUserIdStatement = connection.prepareStatement(sqlFindUserId);
            findUserIdStatement.setString(1, email);
            resultSet = findUserIdStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");

                insertTokenStatement = connection.prepareStatement(sqlInsertToken);
                insertTokenStatement.setString(1, token);
                insertTokenStatement.setInt(2, userId);
                insertTokenStatement.executeUpdate();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for the provided email");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        } finally {
            DaoUtils.closeResources(resultSet, findUserIdStatement, null);
            DaoUtils.closeResources(insertTokenStatement, connection);
        }
    }

    public int getUserId(String token) {
        String sql = "SELECT userId FROM UserSession WHERE sessionId = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("userId");
            } else {
                throw new NotFoundException("User not found for the provided token");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        }
    }


    public void createUser(String firstname, String surname, String email, String hashedPassword, boolean isTeacher, int roleId) {
        String userSql = "INSERT INTO User (firstname, surname, email, wachtwoord, isTeacher, roleId) VALUES (?, ?, ?, ?, ?, ?)";
        String studentSql = "INSERT INTO Student (userId) VALUES (?)";
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement studentStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = databaseConnection.getDatabaseConnection();
            connection.setAutoCommit(false);

            userStatement = connection.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, firstname);
            userStatement.setString(2, surname);
            userStatement.setString(3, email);
            userStatement.setString(4, hashedPassword);
            userStatement.setBoolean(5, isTeacher);
            userStatement.setInt(6, roleId);
            userStatement.executeUpdate();

            generatedKeys = userStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                if (!isTeacher) {
                    studentStatement = connection.prepareStatement(studentSql);
                    studentStatement.setInt(1, userId);
                    studentStatement.executeUpdate();
                }
            } else {
                connection.rollback();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to insert user.");
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage(), e);
        } finally {
            DaoUtils.closeResources(generatedKeys, userStatement, null);
            DaoUtils.closeResources(studentStatement, connection);
        }
    }


}
