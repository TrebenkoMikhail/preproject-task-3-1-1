package habsida.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       authentication.getAuthorities().forEach(authority -> {
           if(authority.getAuthority().equals("ROLE_ADMIN")) {
               try{
                   response.sendRedirect("/admin");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           } else if(authority.getAuthority().equals("ROLE_USER")) {
               try{
                   response.sendRedirect("/user");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
    }
}