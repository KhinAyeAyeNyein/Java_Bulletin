package ojt.scm.bulletin.web.controller.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ojt.scm.bulletin.bl.dto.login.LoginDto;
import ojt.scm.bulletin.bl.dto.user.CreatedUserNameDto;
import ojt.scm.bulletin.bl.dto.user.SearchUserDto;
import ojt.scm.bulletin.bl.dto.user.UserDto;
import ojt.scm.bulletin.bl.service.user.UserService;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Controller
public class UserController {
    /**
     * <h2>USER_TYPE</h2>
     * <p>
     * USER_TYPE
     * </p>
     */
    public String USER_TYPE = "1";
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>encoder</h2>
     * <p>
     * encoder
     * </p>
     */
    @Autowired
    private PasswordEncoder encoder;

    /**
     * <h2>createUserList</h2>
     * <p>
     * method for Creating User List
     * </p>
     *
     * @param session  HttpSession
     * @param userForm SearchUserDto
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView createUserList(HttpSession session, SearchUserDto userForm, HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
        ModelAndView createUserListView = new ModelAndView("userList");
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 10;
        this.getPagination(createUserListView, currentPage, recordsPerPage, false, userForm);
        createUserListView.addObject("searchForm", new SearchUserDto());
        return createUserListView;
    }

    /**
     * <h2>detailUser</h2>
     * <p>
     * Method for Showing User's Detail
     * </p>
     *
     * @param userId  Integer
     * @param request HttpServletRequest
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/detailUser", method = RequestMethod.GET)
    public ModelAndView detailUser(@RequestParam("id") Integer userId, HttpServletRequest request) {
        ModelAndView userView = new ModelAndView("detailUser");
        UserForm detailUser = null;
        try {
            detailUser = this.userService.doGetUserbyId(userId);
        } catch (NullPointerException | IOException e) {
            userView = new ModelAndView("redirect:/error500");
        }
        LoginDto loginUser = (LoginDto) request.getSession().getAttribute("LOGIN_USER");
        if (loginUser.getType().equals(USER_TYPE)) {
            if (loginUser.getId() != detailUser.getId()) {
                userView = new ModelAndView("redirect:/error401");
                return userView;
            }
        }
        userView.addObject("detailUser", detailUser);
        return userView;
    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * Method for Deleting User
     * </p>
     *
     * @param userId  Integer
     * @param request HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") Integer userId, HttpServletRequest request) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        ModelAndView updateView = new ModelAndView();
        this.userService.doDeleteUser(userId, loginUserId);
        this.getPagination(updateView, 1, 10, false, new SearchUserDto());
        updateView.setViewName("redirect:/userList");
        session.setAttribute("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        return updateView;
    }

    /**
     * <h2>createUser</h2>
     * <p>
     * Create User view for Creating new user
     * </p>
     *
     * @param model Model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser(Model model) {
        ModelAndView createUserView = new ModelAndView("createUser");
        createUserView.addObject("rollBackUserForm", new UserForm());
        return createUserView;
    }

    /**
     * <h2>createUserConfirm</h2>
     * <p>
     * Method for showing Confirm view of user creation
     * </p>
     *
     * @param userForm  UserForm
     * @param result    BindingResult
     * @param session   HttpSession
     * @param request   HttpServletRequest
     * @param imageData String
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUserConfirm", params = "confirmUser", method = RequestMethod.POST)
    public ModelAndView createUserConfirm(@ModelAttribute("rollBackUserForm") @Valid UserForm userForm,
            BindingResult result, HttpSession session, HttpServletRequest request,
            @RequestParam("imageData") String imageData) throws ParseException {
        ModelAndView userConfirmView = new ModelAndView("createUser");
        if (result.hasErrors()) {
            userConfirmView.addObject("rollBackUserForm", userForm);
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return userConfirmView;
        }
        if (!userForm.getPassword().equals(request.getParameter("confirmPassword"))) {
            userConfirmView.addObject("rollBackUserForm", userForm);
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0009", null, null));
            return userConfirmView;
        }
        if (userService.isUserExist(userForm.getEmail())) {
            userConfirmView.addObject("rollBackUserForm", userForm);
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0010", null, null));
            return userConfirmView;
        }
        userConfirmView = new ModelAndView("createUserConfirm");
        if (imageData.length() > 0) {
            userForm.setProfile(imageData);
        }
        userConfirmView.addObject("userForm", userForm);
        return userConfirmView;
    }

    /**
     * <h2>insertUser</h2>
     * <p>
     * Method of Do Create and show in list
     * </p>
     *
     * @param userForm UserForm
     * @param result   BindingResult
     * @param request  HttpServletRequest
     * @return
     * @throws ParseException
     * @return ModelAndView
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/insertUser", params = "addUser", method = RequestMethod.POST)
    public ModelAndView insertUser(@ModelAttribute("createUser") @Valid UserForm userForm, BindingResult result,
            HttpServletRequest request) throws ParseException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        String userProfilePath = request.getRealPath("/") + "/resources/profiles/" + userForm.getEmail();
        Path uploadPath = Paths.get(userProfilePath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        userProfilePath = uploadPath + "/" + userForm.getEmail() + ".png";
        this.userService.doCreateUser(userForm, loginUserId, userProfilePath);
        ModelAndView createUserView = new ModelAndView("redirect:/userList");
        return createUserView;
    }

    /**
     * <h2>calcelUserCreate</h2>
     * <p>
     * Method to call if user cancel creation
     * </p>
     *
     * @param userForm UserFormv
     * @param result   BindingResult
     * @param session  HttpSession
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertUser", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUserCreate(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult result,
            HttpSession session) {
        ModelAndView createUserView = new ModelAndView("createUser");
        createUserView.addObject("rollBackUserForm", userForm);
        return createUserView;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * Method to call Update View to edit user
     * </p>
     *
     * @param userID  Integer
     * @param request HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam("id") Integer userID, HttpServletRequest request) {
        ModelAndView updateView = new ModelAndView("updateUser");
        UserForm updateUser = null;
        try {
            updateUser = this.userService.doGetUserbyId(userID);
        } catch (NullPointerException | IOException e) {
            updateView = new ModelAndView("redirect:/error500");
        }
        LoginDto loginUser = (LoginDto) request.getSession().getAttribute("LOGIN_USER");
        if (loginUser.getType().equals(USER_TYPE)) {
            if (loginUser.getId() != updateUser.getId()) {
                updateView = new ModelAndView("redirect:/error401");
                return updateView;
            }
        }
        updateView.addObject("oldUserForm", updateUser);
        return updateView;
    }

    /**
     * <h2>confirmUserUpdate</h2>
     * <p>
     * Method to call user Confirm View
     * </p>
     *
     * @param updateUserForm UserForm
     * @param result         BindingResult
     * @param request        HttpServletRequest
     * @param session        HttpSession
     * @param imageData      String
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/confirmUpdateUser", method = RequestMethod.POST, consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ModelAndView confirmUserUpdate(@ModelAttribute("oldUserForm") @Valid UserForm updateUserForm,
            HttpServletRequest request, BindingResult result, HttpSession session,
            @RequestParam("imageData") String imageData) throws IOException {
        ModelAndView updateConfirmView = new ModelAndView("updateUser");
        if (result.hasErrors()) {
            updateConfirmView.addObject("updateUserForm", updateUserForm);
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
            return updateConfirmView;
        }
        if (this.userService.isUpdateEmailExist(updateUserForm.getEmail(), updateUserForm.getId())) {
            updateConfirmView.addObject("updateUserForm", updateUserForm);
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0010", null, null));
            return updateConfirmView;
        }
        UserForm userById = this.userService.doGetUserbyId(updateUserForm.getId());
        updateConfirmView = new ModelAndView("confirmUpdateUser");
        updateUserForm.setProfile(userById.getProfile());
        if (imageData.length() > 0) {
            updateUserForm.setProfile(imageData);
        }
        updateConfirmView.addObject("updateUserForm", updateUserForm);
        return updateConfirmView;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * To do update user and show on list
     * </p>
     *
     * @param userForm
     * @param result
     * @param session
     * @param request
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/editUser", params = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("updateUserForm") @Valid UserForm userForm, BindingResult result,
            HttpSession session, HttpServletRequest request) throws ParseException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        Path path = Paths.get(request.getRealPath("/") + "/resources/profiles/" + userForm.getEmail());
        String userProfilePath = Files.createDirectories(path) + "/" + userForm.getEmail() + ".png";
        this.userService.doUpdateUser(userForm, loginUserId, userProfilePath);
        ModelAndView updateUserView = new ModelAndView("redirect:/detailUser?id=" + userForm.getId());
        return updateUserView;
    }

    /**
     * <h2>cancelUpdateUser</h2>
     * <p>
     * method to cancel updating user
     * </p>
     *
     * @param userForm
     * @param result
     * @param session
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/editUser", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdateUser(@ModelAttribute("updateUserForm") @Valid UserForm userForm,
            BindingResult result, HttpSession session) {
        ModelAndView updateUserView = new ModelAndView("updateUser");
        updateUserView.addObject("oldUserForm", userForm);
        return updateUserView;
    }

    /**
     * <h2>searchUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param search_input
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public ModelAndView searchUser(@ModelAttribute("searchForm") SearchUserDto search_input,
            HttpServletRequest request) {
        ModelAndView userListView = new ModelAndView("userList");
        if (search_input.equals(null)) {
            this.getPagination(userListView, 1, 10, false, search_input);
            return userListView;
        }
        this.getPagination(userListView, 1, 10, true, search_input);
        userListView.addObject("searchData", search_input);
        return userListView;
    }

    /**
     * <h2>changePassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView changePassword(@RequestParam("id") Integer userId, HttpServletRequest request) {
        ModelAndView updateView = new ModelAndView("changePassword");
        UserForm userById = null;
        try {
            userById = this.userService.doGetUserbyId(userId);
        } catch (IOException e) {
            updateView = new ModelAndView("redirect:/error500");
        }
        updateView.addObject("userId", userById.getId());
        return updateView;
    }

    /**
     * <h2>editPassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView editPassword(@RequestParam("userId") int userId,
            @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword, HttpServletRequest request) {
        ModelAndView updateView = new ModelAndView("changePassword");
        UserForm userById = null;
        try {
            userById = this.userService.doGetUserbyId(userId);
        } catch (IOException e) {
            updateView = new ModelAndView("redirect:/error500");
        }
        boolean isPasswordMatch = encoder.matches(oldPassword, userById.getPassword());
        if (isPasswordMatch == false || !isPasswordMatch) {
            updateView.addObject("userId", userId);
            updateView.addObject("errorMsg", messageSource.getMessage("M_SC_0017", null, null));
            return updateView;
        }
        if (!confirmPassword.equals(newPassword)) {
            updateView.addObject("userId", userId);
            updateView.addObject("errorMsg", messageSource.getMessage("M_SC_0022", null, null));
            return updateView;
        }
        if (newPassword.length() <= 7) {
            updateView.addObject("userId", userId);
            updateView.addObject("errorMsg", messageSource.getMessage("M_SC_0023", null, null));
            return updateView;
        }
        this.userService.doUpdateUserPassword(confirmPassword, userById.getId());
        updateView = new ModelAndView("redirect:/detailUser?id=" + userId);
        return updateView;
    }

    /**
     * <h2>getpagination</h2>
     * <p>
     * 
     * </p>
     *
     * @param userListView
     * @param currentPage
     * @param recordsPerPage
     * @param resultSearch
     * @param userForm
     * @return void
     * @throws ParseException
     */
    private void getPagination(ModelAndView userListView, int currentPage, int recordsPerPage, boolean resultSearch,
            SearchUserDto userForm) {
        try {
            List<UserDto> userList;
            if (userForm.getEmail() == null || userForm.getName() == null || userForm.getFromDate() == null
                    || userForm.getToDate() == null) {
                userList = this.userService.doGetUserList();
            } else {
                userList = this.userService.doGetUserBySearchData(userForm);
            }
            int rows = userList.size();
            int nOfPages = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                nOfPages++;
            }
            List<UserDto> searchUserList = this.userService.doSearchUserByLimit(currentPage, recordsPerPage, userForm);
            List<CreatedUserNameDto> createdUserName = this.userService.doGetCreateNameListById(userForm);
            userListView.addObject("noOfPages", nOfPages);
            userListView.addObject("currentPage", currentPage);
            userListView.addObject("recordsPerPage", recordsPerPage);
            userListView.addObject("userList", searchUserList);
            userListView.addObject("createUserName", createdUserName);
        } catch (ParseException e) {
            userListView = new ModelAndView("redirect:/error500");
        }
    }
}