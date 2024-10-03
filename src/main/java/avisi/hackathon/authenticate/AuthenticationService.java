package avisi.hackathon.authenticate;

import avisi.hackathon.dtos.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationDao authenticationDao;

    @Autowired
    public AuthenticationService(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public LoginResponseDto authenticate(String email, String password) {
        return new LoginResponseDto();
    }

    public void verifyToken(String token) {
        // Implementation
    }
}
