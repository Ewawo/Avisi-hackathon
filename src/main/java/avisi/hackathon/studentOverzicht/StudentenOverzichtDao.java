package avisi.hackathon.studentOverzicht;

import avisi.hackathon.database.DatabaseConnection;
import avisi.hackathon.dtos.CriteriumDto;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentenOverzichtDao {


    private DatabaseConnection databaseConnection = new DatabaseConnection();


    public List<CriteriumDto> getAllCriteriums() throws SQLException {
        List<CriteriumDto> criteriums = new ArrayList<>();
        var newConnection = databaseConnection.getDatabaseConnection();
        PreparedStatement statement = newConnection.prepareStatement("select * from criteria ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CriteriumDto criterium = new CriteriumDto();
            criterium.setId(resultSet.getInt("criteriaId"));
            criterium.setName(resultSet.getString("name"));
            criteriums.add(criterium);
        }
        return criteriums;
    }
}
