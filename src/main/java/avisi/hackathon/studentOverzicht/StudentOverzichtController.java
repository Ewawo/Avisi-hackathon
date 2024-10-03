package avisi.hackathon.studentOverzicht;

import avisi.hackathon.dtos.CriteriumDto;
import avisi.hackathon.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/StudentOverzicht")
public class StudentOverzichtController {

    @Autowired
    private StudentOverzichtService studentOverzichtService;

    @GetMapping("/CriteriaAll")
    public ResponseEntity<List<CriteriumDto>> getAllCriteriums() throws SQLException {
        List<CriteriumDto> criteriumDTOList = studentOverzichtService.getAllCriteriums();
        return ResponseEntity.ok(criteriumDTOList);
    }
}
