package avisi.hackathon.studentInformatie;

import avisi.hackathon.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentInformatieResource {

    @Autowired
    private StudentInformatieService studentInformatieService;

    @GetMapping("api/student/{studentNumber}")
    public ResponseEntity<StudentDTO> studentOverviewResource(@PathVariable String studentNumber) {
        StudentDTO studentDTO = studentInformatieService.studentInformatieService(studentNumber);
        return ResponseEntity.ok(studentDTO);
    }
}
