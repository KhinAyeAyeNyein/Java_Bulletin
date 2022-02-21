package ojt.scm.bulletin.bl.service.login;

import ojt.scm.bulletin.bl.dto.login.LoginDto;
import ojt.scm.bulletin.web.form.LoginForm;

/**
 * <h2>LoginService Class</h2>
 * <p>
 * Process for Displaying LoginService
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface LoginService {
    /**
     * <h2>doGetLoginResult</h2>
     * <p>
     * To Get Login Result
     * </p>
     *
     * @param loginForm
     * @return
     * @return LoginDto
     */
    public LoginDto doGetLoginResult(LoginForm loginForm);
}