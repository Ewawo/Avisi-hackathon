package avisi.hackathon.authenticate;

import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDao {

    public String findPassword(String email) {
        //TODO
        return "password";
    }

    public boolean tokenExists(String token) {
        //TODO
        return false;
    }

    public void destroySession(String token) {
    }
}
