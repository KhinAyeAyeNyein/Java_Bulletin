package ojt.scm.bulletin.bl.service.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import ojt.scm.bulletin.bl.dto.user.CreatedUserNameDto;
import ojt.scm.bulletin.bl.dto.user.SearchUserDto;
import ojt.scm.bulletin.bl.dto.user.UserDto;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface UserService {
    /**
     * <h2>doGetUserList</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return List<UserDto>
     */
    public List<UserDto> doGetUserList();

    /**
     * <h2>doGetUserbyId</h2>
     * <p>
     * Getting user by id
     * </p>
     *
     * @param userId int
     * @return
     * @throws IOException
     * @return UserForm
     */
    public UserForm doGetUserbyId(int userId) throws IOException;

    /**
     * <h2>doGetCreateNameListById</h2>
     * <p>
     * Getting userNameList by id
     * </p>
     *
     * @param userForm UserForm
     * @return
     * @throws ParseException
     * @return CreatedUserNameDto
     */
    public List<CreatedUserNameDto> doGetCreateNameListById(SearchUserDto userForm) throws ParseException;

    /**
     * <h2>doSearchUserByLimit</h2>
     * <p>
     * user list with pagination
     * </p>
     *
     * @param currentPage int
     * @param noOfUser    int
     * @param userForm    UserForm
     * @return
     * @return List<UserDto>
     * @throws ParseException
     */
    public List<UserDto> doSearchUserByLimit(int currentPage, int noOfUser, SearchUserDto search_input)
            throws ParseException;

    /**
     * <h2>doGetUserBySearchData</h2>
     * <p>
     * Getting list of user Search list
     * </p>
     *
     * @param userForm UserForm
     * @return
     * @return List<UserDto>
     * @throws ParseException
     */
    public List<UserDto> doGetUserBySearchData(SearchUserDto userForm) throws ParseException;

    /**
     * <h2>isUserExist</h2>
     * <p>
     * to check user exist
     * </p>
     *
     * @param email String
     * @return
     * @return boolean
     */
    public boolean isUserExist(String email);

    /**
     * 
     * <h2>doCreateUser</h2>
     * <p>
     * Creating a user with profile value
     * </p>
     *
     * @param userForm        UserForm
     * @param currentUserId   int
     * @param userProfilePath String
     * @return
     * @throws IOException
     * @return void
     */
    public void doCreateUser(UserForm userForm, int currentUserId, String userProfilePath)
            throws ParseException, IOException;

    /**
     * <h2>isUpdateEmailExist</h2>
     * <p>
     * to check if update email exist
     * </p>
     *
     * @param email  String
     * @param userId int
     * @return
     * @return boolean
     */
    public boolean isUpdateEmailExist(String email, int userId);

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update user
     * </p>
     *
     * @param userForm        UserForm
     * @param currentUserId   int
     * @param userProfilePath String
     * @return
     * @throws IOException
     * @return void
     */
    public void doUpdateUser(UserForm userForm, int currentUserId, String userProfilePath)
            throws ParseException, IOException;

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete user
     * </p>
     *
     * @param userId int
     * @return void
     */
    public void doDeleteUser(int userId, int loginId);

    /**
     * <h2>doUpdateUserPassword</h2>
     * <p>
     * Change user password
     * </p>
     *
     * @param newPassword String
     * @param userId      int
     * @return void
     */
    public void doUpdateUserPassword(String newPassword, int userId);
}