package avisi.hackathon.studentOverzicht;

import avisi.hackathon.dtos.CriteriumDto;
import avisi.hackathon.dtos.KerntaaktDto;
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

    public List<KerntaaktDto> getAllKerntaken() throws SQLException {
        return studentenOverzichtDao.getAllKerntaken();
    }

    public CriteriumDto getCriteriumdetail(int criteriaId) throws SQLException {
        return studentenOverzichtDao.getCriterium(criteriaId);
    }
}
