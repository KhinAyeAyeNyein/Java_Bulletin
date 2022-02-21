package ojt.scm.bulletin.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import ojt.scm.bulletin.persistence.entity.User;

/**
 * <h2>LoginForm Class</h2>
 * <p>
 * Process for Displaying LoginForm
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public class LoginForm {
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    @Email
    @NotEmpty
    private String email;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @NotEmpty
    private String password;

    /**
     * <h2>getEmail</h2>
     * <p>
     * Getter Method for Email
     * </p>
     *
     * @return
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * <h2>setEmail</h2>
     * <p>
     * Setter Method for Email
     * </p>
     *
     * @param email
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>getPassword</h2>
     * <p>
     * Getter method for password
     * </p>
     *
     * @return
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * <h2>setPassword</h2>
     * <p>
     * Setter Method for Password
     * </p>
     *
     * @param password
     * @return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <h2>Constructor for LoginForm</h2>
     * <p>
     * Constructor for LoginForm
     * </p>
     */
    public LoginForm() {

    }

    /**
     * <h2>Constructor for LoginForm</h2>
     * <p>
     * Constructor for LoginForm
     * </p>
     * 
     * @param user
     */
    public LoginForm(User user) {
        super();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}