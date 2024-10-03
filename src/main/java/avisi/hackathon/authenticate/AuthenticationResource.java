package avisi.hackathon.authenticate;

import avisi.hackathon.annotations.Authenticate;
import avisi.hackathon.dtos.LoginRequestDto;
import avisi.hackathon.dtos.LoginResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @Authenticate
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> test() {
        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setRole("USER");
        loginResponse.setToken("gerda token");
        return ResponseEntity.ok(loginResponse);
    }
}
