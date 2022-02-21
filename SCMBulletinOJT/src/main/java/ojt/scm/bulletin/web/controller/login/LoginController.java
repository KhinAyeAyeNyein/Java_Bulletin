package ojt.scm.bulletin.web.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ojt.scm.bulletin.bl.dto.login.LoginDto;
import ojt.scm.bulletin.bl.service.login.LoginService;
import ojt.scm.bulletin.web.form.LoginForm;

/**
 * <h2>Login Class</h2>
 * <p>
 * Process for Displaying Login
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Controller
public class LoginController {
    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private LoginService loginService;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>showLogin</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/showLogin", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        ModelAndView loginView = new ModelAndView("login");
        loginView.addObject("loginForm", new LoginDto());
        return loginView;
    }

    /**
     * <h2>showUserLogin</h2>
     * <p>
     * 
     * </p>
     *
     * @param loginForm
     * @param result
     * @param session
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/showLogin", params = "login", method = RequestMethod.POST)
    public ModelAndView showUserLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView userCView = new ModelAndView("login");
        if (result.hasErrors()) {
            return userCView;
        }
        LoginDto loginUser = this.loginService.doGetLoginResult(loginForm);
        if (loginUser != null && (loginUser.getPassword() != null && loginUser.getEmail() != null)) {
            session.setAttribute("LOGIN_USER", loginUser);
            session.setAttribute("loginUserId", loginUser.getId());
            userCView = new ModelAndView("redirect:/postList");
        } else {
            userCView = new ModelAndView("login");
            userCView.addObject("errorMsg", messageSource.getMessage("M_SC_0001", null, null));
        }
        return userCView;
    }

    /**
     * <h2>logout</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @param session
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        session.removeAttribute("LOGIN_USER");
        ModelAndView loginView = new ModelAndView("redirect:/showLogin");
        return loginView;
    }

}