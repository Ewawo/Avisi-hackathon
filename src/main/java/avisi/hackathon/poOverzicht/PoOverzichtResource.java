package avisi.hackathon.poOverzicht;

import avisi.hackathon.annotations.Authenticate;
import avisi.hackathon.dtos.StudentDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PoOverzichtResource {

    @Autowired
    private PoOverzichtService poOverzichtService;

    @Authenticate
    @GetMapping("api/students")
    public ResponseEntity<List<StudentDTO>> studentOverviewResource() {
        List<StudentDTO> studentDTOList = poOverzichtService.studentOverviewService();
        return ResponseEntity.ok(studentDTOList);
    }
}
