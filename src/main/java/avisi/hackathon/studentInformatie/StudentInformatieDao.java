package avisi.hackathon.studentInformatie;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.stereotype.Repository;


@Repository
public class StudentInformatieDao {
    public StudentDTO studentInformatieDao(String studentNumber) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentNumber("1");
        studentDTO.setName("Sander");
        studentDTO.setInfix("");
        studentDTO.setGender("Male");
        studentDTO.setSurname("Evers");
        studentDTO.setBirthDate("23-10-2004");
        studentDTO.setClassCode("1A");
        studentDTO.setCreboNumber("1");
        return studentDTO;
    }
}
