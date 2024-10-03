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
        studentDTOList.add(studentDTO);
        return studentDTOList;
    }
}
