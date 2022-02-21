package ojt.scm.bulletin.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ojt.scm.bulletin.bl.dto.login.LoginDto;

/**
 * <h2>AuthorizationInterceptor Class</h2>
 * <p>
 * Process for Displaying AuthorizationInterceptor
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@PropertySource("classpath:app.properties")
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    /**
     * <h2> encoder</h2>
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
     * <h2>environment</h2>
     * <p>
     * environment
     * </p>
     */
    @Autowired
    private Environment environment;

    /**
     * <h2>MAX_INACTIVE_SESSION_TIME</h2>
     * <p>
     * MAX_INACTIVE_SESSION_TIME
     * </p>
     */
    private static final long MAX_INACTIVE_SESSION_TIME = 50 * 10000;

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
        LoginDto loginUser = (LoginDto) request.getSession().getAttribute("LOGIN_USER");
        if ((loginUser == null)) {
            return true;
        }
        String loginUserType = loginUser.getType();
        if (loginUserType == "0") {
            return true;
        }
        long time = System.currentTimeMillis() - request.getSession().getLastAccessedTime();
        if (time > MAX_INACTIVE_SESSION_TIME) {
            response.sendRedirect(request.getContextPath() + "/logout");
        }
        String pathsKey = new String("authorized.path.").concat(loginUserType.toString());
        String paths = environment.getProperty(pathsKey);
        String servletPath = request.getServletPath();
        if (paths.contains(servletPath)) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/error401");
            return false;
        }
    }
}