package avisi.hackathon.poOverzicht;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoOverzichtService {

    PoOverzichtDao poOverzichtDao;

    public List<StudentDTO> studentOverviewService() {
        return poOverzichtDao.studentOverviewDao();
    }
}
