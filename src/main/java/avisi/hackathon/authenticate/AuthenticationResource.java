package avisi.hackathon.authenticate;

import avisi.hackathon.dtos.LoginRequestDto;
import avisi.hackathon.dtos.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationResource (AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto loginResponse = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(loginResponse);
    }
}
