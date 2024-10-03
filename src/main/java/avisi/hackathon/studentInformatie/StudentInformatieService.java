package avisi.hackathon.studentInformatie;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInformatieService {

    @Autowired
    StudentInformatieDao studentInformatieDao;

    public StudentDTO studentInformatieService(String studentNumber) {
        return studentInformatieDao.studentInformatieDao(studentNumber);
    }
}
