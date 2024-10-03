package avisi.hackathon.authenticate;

import avisi.hackathon.dtos.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final AuthenticationDao authenticationDao;

    @Autowired
    public AuthenticationService(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public LoginResponseDto authenticate(String email, String password) {
        String passwordHash = authenticationDao.findPassword(email);

        try {
            // Check if the password matches the hash
            if (passwordHash == null || !BCrypt.checkpw(password, passwordHash)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("BCrypt encountered an invalid salt: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(generateToken());

        return loginResponse;
    }



    public void verifyToken(String token) {
        // Implementation
    }

    private String generateToken() {
        String token;
        do {
            token = UUID.randomUUID().toString();
        } while (authenticationDao.tokenExists(token));
        return token;
    }
}
