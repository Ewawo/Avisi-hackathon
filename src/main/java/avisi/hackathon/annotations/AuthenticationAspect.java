package avisi.hackathon.annotations;

import avisi.hackathon.authenticate.AuthenticationService;
import avisi.hackathon.exceptions.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestAttributes;

@Aspect
@Component
public class AuthenticationAspect {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationAspect(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Before("@annotation(Authenticate)")
    public void authenticateAccess() throws UnauthorizedException {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes attrs)) {
            throw new UnauthorizedException("No request attributes found.");
        }

        String token = attrs.getRequest().getHeader("Authorization");

        if (token == null) {
            throw new UnauthorizedException("No authorization token provided.");
        }

        authenticationService.verifyToken(token);
    }
}