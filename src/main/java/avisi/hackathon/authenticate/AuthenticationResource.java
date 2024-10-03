package avisi.hackathon.authenticate;

import avisi.hackathon.annotations.Authenticate;
import avisi.hackathon.dtos.LoginRequestDto;
import avisi.hackathon.dtos.LoginResponseDto;
import avisi.hackathon.dtos.RegisterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @DeleteMapping
    public HttpStatus logout() {
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequest) {
        authenticationService.register(
                registerRequest.getFirstname(),
                registerRequest.getSurname(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.isTeacher()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
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
