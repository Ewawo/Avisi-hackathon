package avisi.hackathon.poOverzicht;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PoOverzichtDao {
    public List<StudentDTO> studentOverviewDao() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentNumber("1");
        studentDTO.setName("Sander");
        studentDTO.setInfix("");
        studentDTO.setGender("Male");
        studentDTO.setSurname("Evers");
        studentDTO.setBirthDate("23-10-2004");
        studentDTO.setClassCode("1A");
        studentDTO.setCreboNumber("1");

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setStudentNumber("1");
        studentDTO1.setName("Sander");
        studentDTO1.setInfix("");
        studentDTO1.setGender("Male");
        studentDTO1.setSurname("Evers");
        studentDTO1.setBirthDate("23-10-2004");
        studentDTO1.setClassCode("1A");
        studentDTO1.setCreboNumber("1");

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setStudentNumber("1");
        studentDTO2.setName("Sander");
        studentDTO2.setInfix("");
        studentDTO2.setGender("Male");
        studentDTO2.setSurname("Evers");
        studentDTO2.setBirthDate("23-10-2004");
        studentDTO2.setClassCode("1A");
        studentDTO2.setCreboNumber("1");

        StudentDTO studentDTO3 = new StudentDTO();
        studentDTO3.setStudentNumber("1");
        studentDTO3.setName("Sander");
        studentDTO3.setInfix("");
        studentDTO3.setGender("Male");
        studentDTO3.setSurname("Evers");
        studentDTO3.setBirthDate("23-10-2004");
        studentDTO3.setClassCode("1A");
        studentDTO3.setCreboNumber("1");

        studentDTOList.add(studentDTO3);
        studentDTOList.add(studentDTO2);
        studentDTOList.add(studentDTO1);
        studentDTOList.add(studentDTO);
        return studentDTOList;
    }
}
