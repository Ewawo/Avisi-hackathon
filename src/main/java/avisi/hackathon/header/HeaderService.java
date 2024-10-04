package avisi.hackathon.header;

import avisi.hackathon.authenticate.AuthenticationService;
import avisi.hackathon.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Primary
public class HeaderService {

    private AuthenticationService authenticationService;


    @Autowired
    public HeaderService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public int getUserId() {
        String token = this.getToken();
        return authenticationService.getUserId(token);
    }

    public String getToken() {
        return this.token();
    }

    private String token() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        String token = requestAttributes.getRequest().getHeader("Authorization");
        if (token == null) {
            throw new UnauthorizedException("No authorization token provided.");
        }

        return token;
    }
}