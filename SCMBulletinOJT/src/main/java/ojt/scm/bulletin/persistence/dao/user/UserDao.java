package ojt.scm.bulletin.persistence.dao.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ojt.scm.bulletin.bl.dto.user.SearchUserDto;
import ojt.scm.bulletin.persistence.entity.User;

/**
 * <h2>UserDao Class</h2>
 * <p>
 * Process for Displaying UserDao
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface UserDao {
    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * check email is unique and get user
     * </p>
     *
     * @param email String
     * @return
     * @return User
     */
    public User dbGetUserByEmail(String email);

    /**
     * <h2>dbUpdatedUserExistList</h2>
     * <p>
     * Check update email exist
     * </p>
     *
     * @param email String
     * @return
     * @return List<User>
     */
    public List<User> dbUpdatedUserExistList(String email);

    /**
     * <h2>dbGetUserList</h2>
     * <p>
     * Getting list of user
     * </p>
     *
     * @return
     * @return List<User>
     */
    public List<User> dbGetUserList();

    /**
     * <h2>dbGetUserbyID</h2>
     * <p>
     * Getting user by id
     * </p>
     *
     * @param userId int
     * @return
     * @return User
     */
    public User dbGetUserbyID(int userId);

    /**
     * <h2>dbGetNameByID</h2>
     * <p>
     * Getting name by created user id
     * </p>
     *
     * @param userId int
     * @return
     * @return String
     */
    public String dbGetNameByID(int userId);

    /**
     * <h2>dbGetUserListWithLimit</h2>
     * <p>
     * getting user list with pagination
     * </p>
     *
     * @param currentPage int
     * @param noOfUser    int
     * @param userForm    UserForm
     * @return
     * @return List<User>
     * @throws ParseException
     */
    public List<User> dbGetUserListWithLimit(int currentPage, int noOfUser, SearchUserDto userForm)
            throws ParseException;

    /**
     * <h2>dbGetUserListBySearchData</h2>
     * <p>
     * Getting list of search user
     * </p>
     *
     * @param userForm UserForm
     * @return
     * @return List<User>
     * @throws ParseException
     */
    public List<User> dbGetUserListBySearchData(SearchUserDto userForm) throws ParseException;

    /**
     * <h2>dbCreateUser</h2>
     * <p>
     * Creating a new User
     * </p>
     *
     * @param user         User
     * @param createUserId int
     * @param currentDate  Date
     * @return void
     */
    public void dbCreateUser(User user, int createUserId, Date currentDate);

    /**
     * <h2>dbEditUser</h2>
     * <p>
     * Update user
     * </p>
     *
     * @param user         User
     * @param updateUserId int
     * @param currentDate  Date
     * @return
     * @return void
     */
    public void dbEditUser(User user, int updateUserId, Date currentDate);

    /**
     * <h2>dbUpdatePassword</h2>
     * <p>
     * change user password
     * </p>
     *
     * @param newPassword String
     * @param userId      int
     * @return void
     */
    public void dbUpdatePassword(String newPassword, int userId);
}