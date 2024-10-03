package avisi.hackathon.annotations;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class AdminAspect {

    @Before("@annotation(Admin)")
    public void isAdmin() throws Exception {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attrs.getRequest();

        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new Exception("No authorization token provided.");
        }
    }
}