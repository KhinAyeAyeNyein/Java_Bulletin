package ojt.scm.bulletin.bl.service.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ojt.scm.bulletin.bl.dto.login.LoginDto;
import ojt.scm.bulletin.bl.service.login.LoginService;
import ojt.scm.bulletin.persistence.dao.user.UserDao;
import ojt.scm.bulletin.persistence.entity.User;
import ojt.scm.bulletin.web.form.LoginForm;

/**
 * <h2>LoginServiceImpl Class</h2>
 * <p>
 * Process for Displaying LoginServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Transactional
@Service
@Primary
public class LoginServiceImpl implements LoginService {
    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>encoder</h2>
     * <p>
     * encoder
     * </p>
     */
    @Autowired
    private PasswordEncoder encoder;

    /**
     * <h2>doGetLoginResult</h2>
     * <p>
     * to Get Login Result
     * </p>
     *
     * @param loginForm
     * @return
     * @return LoginDto
     */
    @Override
    public LoginDto doGetLoginResult(LoginForm loginForm) {
        User user = this.userDao.dbGetUserByEmail(loginForm.getEmail());
        LoginDto loginDto = new LoginDto();
        if (user != null) {
            boolean isPasswordMatch = encoder.matches(loginForm.getPassword(), user.getPassword());
            if (isPasswordMatch) {
                loginDto.setEmail(user.getEmail());
                loginDto.setPassword(user.getPassword());
                loginDto.setType(user.getType());
                loginDto.setId(user.getId());
            }
        }
        return loginDto;
    }
}