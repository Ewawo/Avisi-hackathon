package avisi.hackathon.studentOverzicht;

import avisi.hackathon.dtos.CriteriumDto;
import avisi.hackathon.dtos.KerntaaktDto;
import avisi.hackathon.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/StudentOverzicht")
public class StudentOverzichtController {

    @Autowired
    private StudentOverzichtService studentOverzichtService;

    @GetMapping(path = "/CriteriaAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CriteriumDto>> getAllCriteriums() throws SQLException {
        List<CriteriumDto> criteriumDTOList = studentOverzichtService.getAllCriteriums();
        return ResponseEntity.ok(criteriumDTOList);
    }

    @GetMapping(path = "/Criteria/{criteriaId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CriteriumDto> getCriteriumdetail(@PathVariable("criteriaId" ) int criteriaId) throws SQLException {
        CriteriumDto criteriumDTOList = studentOverzichtService.getCriteriumdetail(criteriaId);
        return ResponseEntity.ok(criteriumDTOList);
    }


    @GetMapping(path = "/KerntakenAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KerntaaktDto>> getAllKerntaken() throws SQLException {
        List<KerntaaktDto> kerntaaktDtoList = studentOverzichtService.getAllKerntaken();
        return ResponseEntity.ok(kerntaaktDtoList);
    }



}
