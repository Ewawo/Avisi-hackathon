package avisi.hackathon.authenticate;

import avisi.hackathon.dtos.LoginResponseDto;
import avisi.hackathon.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
                throw new UnauthorizedException("Invalid email or password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("BCrypt encountered an invalid salt: " + e.getMessage());
            throw new UnauthorizedException("Invalid email or password");
        }

        LoginResponseDto loginResponse = new LoginResponseDto();
        String token = generateToken();
        loginResponse.setToken(token);
        authenticationDao.insertToken(email, token);
        loginResponse.setRole(authenticationDao.getUser(token));

        return loginResponse;
    }

    public void logout(String token) {
        authenticationDao.destroySession(token);
    }



    public void verifyToken(String token) {
        // Implementation
        if(!authenticationDao.tokenExists(token)) {
            throw new UnauthorizedException("Invalid token");

        }
    }

    public void destroySession(String token) {
        authenticationDao.destroySession(token);
    }

    private String generateToken() {
        String token;
        do {
            token = UUID.randomUUID().toString();
        } while (authenticationDao.tokenExists(token));
        return token;
    }

    public void register(String firstname, String surname, String email, String password, boolean isTeacher, int roleId) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        authenticationDao.createUser(firstname, surname, email, hashedPassword, isTeacher, roleId);
    }

    public int getUserId(String token) {
        return authenticationDao.getUserId(token);
    }
}
