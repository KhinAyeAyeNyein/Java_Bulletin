package ojt.scm.bulletin.bl.service.user.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ojt.scm.bulletin.bl.dto.user.CreatedUserNameDto;
import ojt.scm.bulletin.bl.dto.user.SearchUserDto;
import ojt.scm.bulletin.bl.dto.user.UserDto;
import ojt.scm.bulletin.bl.service.user.UserService;
import ojt.scm.bulletin.persistence.dao.user.UserDao;
import ojt.scm.bulletin.persistence.entity.User;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Transactional
@Service
@Primary
public class UserServiceImpl implements UserService {
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
     * <h2>doGetUserList</h2>
     * <p>
     * Getting User List
     * </p>
     *
     * @return
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> doGetUserList() {
        List<User> userForm = (List<User>) this.userDao.dbGetUserList();
        List<UserDto> listUserDTO = new ArrayList<>();
        for (User user : userForm) {
            UserDto entity2Dto = new UserDto(user);
            listUserDTO.add(entity2Dto);
        }
        return listUserDTO;
    }

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
    @SuppressWarnings("resource")
    @Override
    public UserForm doGetUserbyId(int userId) throws IOException {
        User resultUser = this.userDao.dbGetUserbyID(userId);
        if (resultUser == null || resultUser.equals(null)) {
            throw new NullPointerException();
        }
        UserForm resultUserForm = new UserForm(resultUser);
        if (resultUserForm.getProfile() != null) {
            String userProfilePath = resultUserForm.getProfile();
            File userProfile = new File(userProfilePath);
            resultUserForm.setProfile(null);
            if (userProfile.exists()) {
                FileInputStream fis = new FileInputStream(userProfile);
                byte byteArray[] = new byte[(int) userProfile.length()];
                fis.read(byteArray);
                String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
                resultUserForm.setProfile(imageString);
            }
        }
        return resultUserForm;
    }

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
    @Override
    public List<CreatedUserNameDto> doGetCreateNameListById(SearchUserDto userForm) throws ParseException {
        List<CreatedUserNameDto> createdusername = new ArrayList<CreatedUserNameDto>();
        List<User> userList = this.userDao.dbGetUserListBySearchData(userForm);
        for (User user : userList) {
            CreatedUserNameDto createdUser = new CreatedUserNameDto(user);
            int createdUserId = createdUser.getCreatedUserId();
            String name = userDao.dbGetNameByID(createdUserId);
            createdUser.setName(name);
            createdusername.add(createdUser);
        }
        return createdusername;
    }

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
    @Override
    public List<UserDto> doSearchUserByLimit(int currentPage, int noOfUser, SearchUserDto userForm)
            throws ParseException {
        List<UserDto> userDTOList = new ArrayList<UserDto>();
        List<User> userList = this.userDao.dbGetUserListWithLimit(currentPage, noOfUser, userForm);
        for (User user : userList) {
            UserDto userDTO = new UserDto(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

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
    @Override
    public List<UserDto> doGetUserBySearchData(SearchUserDto userForm) throws ParseException {
        List<UserDto> userDTOList = new ArrayList<UserDto>();
        List<User> userList = this.userDao.dbGetUserListBySearchData(userForm);
        for (User user : userList) {
            UserDto userDTO = new UserDto(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

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
    @Override
    public boolean isUserExist(String email) {
        boolean userExist = false;
        User user = this.userDao.dbGetUserByEmail(email);
        if (user != null) {
            userExist = true;
        }
        return userExist;
    }

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
    @Override
    public void doCreateUser(UserForm userForm, int currentUserId, String userProfilePath) throws IOException {
        String imageBase64 = userForm.getProfile();
        if (!imageBase64.isEmpty() && !imageBase64.equals("") && !imageBase64.equals(null)) {
            String[] block = imageBase64.split(",");
            String realData = block[1];
            byte[] data = Base64.decodeBase64(realData);
            try (FileOutputStream stream = new FileOutputStream(userProfilePath)) {
                stream.write(data);
            }
            userForm.setProfile(userProfilePath);
        }
        String pw = userForm.getPassword();
        String pwEncode = encoder.encode(pw);
        userForm.setPassword(pwEncode);
        User user = new User(userForm);
        userDao.dbCreateUser(user, currentUserId, new Date());
    }

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
    @Override
    public boolean isUpdateEmailExist(String email, int userId) {
        boolean updateEmailExist = false;
        List<User> userList = userDao.dbUpdatedUserExistList(email);
        User userById = userDao.dbGetUserbyID(userId);
        if (userList != null) {
            for (User user : userList) {
                if (userById != null) {
                    if (user.getEmail() != userById.getEmail()) {
                        updateEmailExist = true;
                    }
                }
            }
        }
        return updateEmailExist;
    }

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
    @Override
    public void doUpdateUser(UserForm userForm, int currentUserId, String userProfilePath) throws IOException {
        User user = new User(userForm);
        Date currentDate = new Date();
        String updateImagePath = userForm.getProfile();
        if (userProfilePath.length() > 0) {
            if (updateImagePath.length() > 0 && !updateImagePath.equals(userProfilePath)) {
                System.out.println(userProfilePath);
                File deletedOldProfile = new File(userProfilePath);
                deletedOldProfile.delete();
                String imageBase64 = userForm.getProfile();
                String[] block = imageBase64.split(",");
                String realData = block[1];
                byte[] data = Base64.decodeBase64(realData);
                try (FileOutputStream stream = new FileOutputStream(userProfilePath)) {
                    stream.write(data);
                }
            }
        } else {
            userProfilePath = updateImagePath;
        }
        user.setProfile(userProfilePath);
        User updatedUser = this.userDao.dbGetUserbyID(userForm.getId());
        if (updatedUser != null) {
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setType(user.getType());
            updatedUser.setDob(user.getDob());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setProfile(user.getProfile());
            updatedUser.setAddress(user.getAddress());
        }
        this.userDao.dbEditUser(updatedUser, currentUserId, currentDate);
    }

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete user
     * </p>
     *
     * @param userId int
     * @return void
     */
    @Override
    public void doDeleteUser(int userId, int deletedUserId) {
        User deletedUser = this.userDao.dbGetUserbyID(userId);
        deletedUser.setDeletedAt(new Date());
        deletedUser.setDeletedUserId(deletedUserId);
    }

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
    @Override
    public void doUpdateUserPassword(String newPassword, int userId) {
        String pwEncode = encoder.encode(newPassword);
        this.userDao.dbUpdatePassword(pwEncode, userId);
    }
}