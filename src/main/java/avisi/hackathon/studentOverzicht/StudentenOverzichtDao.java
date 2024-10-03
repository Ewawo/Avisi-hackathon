package avisi.hackathon.studentOverzicht;

import avisi.hackathon.database.databaseConnection;
import avisi.hackathon.dtos.CriteriumDto;
import avisi.hackathon.dtos.KerntaaktDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentenOverzichtDao {


    private databaseConnection databaseConnection = new databaseConnection();


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

    public List<KerntaaktDto> getAllKerntaken() throws SQLException {
        List<KerntaaktDto> kerntaaktDtos = new ArrayList<>();
        var newConnection = databaseConnection.getDatabaseConnection();
        PreparedStatement statement = newConnection.prepareStatement("select * from coretask ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            KerntaaktDto kerntaaktDto = new KerntaaktDto();
            kerntaaktDto.setId(resultSet.getInt("coretaskId"));
            kerntaaktDto.setName(resultSet.getString("Name"));

            kerntaaktDtos.add(kerntaaktDto);

        }
        return kerntaaktDtos;
    }

    public CriteriumDto getCriterium(int criteriaId) throws SQLException {
        CriteriumDto criteriumDto  = new CriteriumDto();
        var newConnection = databaseConnection.getDatabaseConnection();
        PreparedStatement statement = newConnection.prepareStatement("select CT.name as coretaskName, C.name as criterianame,W.name as workprocessName ,  C.criteriaId , C.description, C.onvoldoende,C.orientatieVoldoende,C.orientatieGoed,C.developervoldoende,C.developerGoed,C.expertVoldoende,C.expertGoed from criteria C JOIN workprocess W ON C.workProcessId = W.workProcessId JOIN coretask CT ON CT.coreTaskId = W.coreTaskId where C.criteriaId = ?");
        statement.setInt(1, criteriaId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            criteriumDto.setName(resultSet.getString("criterianame"));
            criteriumDto.setId(resultSet.getInt("criteriaId"));
            criteriumDto.setKerntaak(resultSet.getString("CoretaskName"));
            criteriumDto.setWerkprocess(resultSet.getString("workprocessName"));
            criteriumDto.setOnvoldoende(resultSet.getString("onvoldoende"));
            criteriumDto.setOrientatieVoldoende(resultSet.getString("orientatieVoldoende"));
            criteriumDto.setOrientatieGoed(resultSet.getString("orientatieGoed"));
            criteriumDto.setDeveloperVoldoende(resultSet.getString("developervoldoende"));
            criteriumDto.setDeveloperGoed(resultSet.getString("developerGoed"));
            criteriumDto.setExpertVoldoende(resultSet.getString("expertVoldoende"));
            criteriumDto.setExpertGoed(resultSet.getString("expertGoed"));
        }
        return criteriumDto;
    }
}
