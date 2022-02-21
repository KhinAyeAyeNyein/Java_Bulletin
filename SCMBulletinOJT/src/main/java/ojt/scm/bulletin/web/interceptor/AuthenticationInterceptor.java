package ojt.scm.bulletin.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <h2>AuthenticationInterceptor Class</h2>
 * <p>
 * Process for Displaying AuthenticationInterceptor
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    /**
     * <h2>encoder</h2>
     * <p>
     * Creating bean method for encoder
     * </p>
     *
     * @return
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * <h2>preHandle</h2>
     * <p>
     * 
     * </p>
     * 
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("LOGIN_USER") == null) {
            response.sendRedirect(request.getContextPath() + "/showLogin");
            return false;
        } else {
            return true;
        }
    }
}