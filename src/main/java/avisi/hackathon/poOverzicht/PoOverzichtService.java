package avisi.hackathon.poOverzicht;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoOverzichtService {

    @Autowired
    PoOverzichtDao poOverzichtDao;

    public List<StudentDTO> studentOverviewService() {
        return poOverzichtDao.studentOverviewDao();
    }
}
