package avisi.hackathon.studentOverzicht;

import avisi.hackathon.dtos.CriteriumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentOverzichtService {

    @Autowired
    private StudentenOverzichtDao studentenOverzichtDao;

    public List<CriteriumDto> getAllCriteriums() throws SQLException {
        return studentenOverzichtDao.getAllCriteriums();
    }
}
