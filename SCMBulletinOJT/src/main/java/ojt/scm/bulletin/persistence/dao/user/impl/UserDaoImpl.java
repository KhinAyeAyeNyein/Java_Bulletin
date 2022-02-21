package ojt.scm.bulletin.persistence.dao.user.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.scm.bulletin.bl.dto.user.SearchUserDto;
import ojt.scm.bulletin.persistence.dao.user.UserDao;
import ojt.scm.bulletin.persistence.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Process for Displaying UserDaoImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@SuppressWarnings("deprecation")
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * <h2>SELECT_EMAIL_BY_HQL</h2>
     * <p>
     * SELECT_EMAIL_BY_HQL
     * </p>
     */
    public static String SELECT_USER_BY_EMAIL_HQL = "SELECT u FROM User u "
                                                  + "WHERE u.email = :email ";

    /**
     * <h2>SELECT_USER_LIST_BY_HQL</h2>
     * <p>
     * SELECT_USER_LIST_BY_HQL
     * </p>
     */
    public static String SELECT_USER_LIST_HQL = "FROM User u "
                                              + "WHERE u.deletedAt IS NULL ";

    /**
     * <h2>SELECT_USER_BY_ID_HQL</h2>
     * <p>
     * SELECT_USER_BY_ID_HQL
     * </p>
     */
    public static String SELECT_USER_BY_ID_HQL = "FROM User u "
                                               + "WHERE u.id = :id ";

    /**
     * <h2>UPDATE_PASSWORD_HQL</h2>
     * <p>
     * UPDATE_PASSWORD_HQL
     * </p>
     */
    private static String UPDATE_PASSWORD_HQL = "UPDATE User u SET u.password = :newPassword "
                                              + "WHERE u.id = :userId ";

    /**
     * <h2>SELECT_NAME_WITH_ID_HQL</h2>
     * <p>
     * SELECT_NAME_WITH_ID_HQL
     * </p>
     */
    private static String SELECT_NAME_WITH_ID_HQL = "SELECT u.name FROM User u "
                                                  + "WHERE u.id = :id ";

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>formatter</h2>
     * <p>
     * formatter to format Date
     * </p>
     */
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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
    @SuppressWarnings("unchecked")
    @Override
    public User dbGetUserByEmail(String email) {
        Query<User> queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_EMAIL_HQL);
        queryUserByEmail.setParameter("email", email);
        User user = queryUserByEmail.uniqueResult();
        return user;
    }

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<User> dbUpdatedUserExistList(String email) {
        Query queryUser = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_EMAIL_HQL);
        queryUser.setParameter("email", email);
        List<User> userList = (List<User>) queryUser.list();
        return userList;
    }

    /**
     * <h2>dbGetUserList</h2>
     * <p>
     * Getting list of user
     * </p>
     *
     * @return
     * @return List<User>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> dbGetUserList() {
        Query<User> queryUserList = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_LIST_HQL);
        List<User> userlist = (List<User>) queryUserList.list();
        return userlist;
    }

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
    @SuppressWarnings("rawtypes")
    @Override
    public User dbGetUserbyID(int userId) {
        Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_ID_HQL);
        queryUserById.setParameter("id", userId);
        User resultStudent = (User) queryUserById.uniqueResult();
        return resultStudent;
    }

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
    @SuppressWarnings("rawtypes")
    @Override
    public String dbGetNameByID(int createdUserId) {
        Query queryNameById = this.sessionFactory.getCurrentSession().createQuery(SELECT_NAME_WITH_ID_HQL);
        queryNameById.setParameter("id", createdUserId);
        String getName = (String) queryNameById.getSingleResult();
        return getName;
    }

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<User> dbGetUserListWithLimit(int currentPage, int noOfUser, SearchUserDto userForm)
            throws ParseException {
        int start = currentPage * noOfUser - noOfUser;
        Query queryUserList = dbCheckQuery(userForm);
        queryUserList.setFirstResult(start);
        queryUserList.setMaxResults(noOfUser);
        List<User> userList = (List<User>) queryUserList.list();
        return userList;
    }

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<User> dbGetUserListBySearchData(SearchUserDto userForm) throws ParseException {
        Query queryUserList = dbCheckQuery(userForm);
        List<User> userList = (List<User>) queryUserList.list();
        return userList;
    }

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
    @Override
    public void dbCreateUser(User user, int createUserId, Date currentDate) {
        user.setCreatedUserId(createUserId);
        user.setCreatedAt(currentDate);
        this.sessionFactory.getCurrentSession().save(user);
    }

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
    @Override
    public void dbEditUser(User user, int updateUserId, Date currentDate) {
        user.setUpdatedUserId(updateUserId);
        user.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().update(user);
    }

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
    @SuppressWarnings("rawtypes")
    @Override
    public void dbUpdatePassword(String newPassword, int userId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(UPDATE_PASSWORD_HQL);
        query.setParameter("userId", userId);
        query.setParameter("newPassword", newPassword);
        query.executeUpdate();
    }

    /**
     * <h2>dbCheckQuery</h2>
     * <p>
     * Check Query to get user list
     * </p>
     *
     * @param userForm UserForm
     * @return
     * @throws ParseException
     * @return Query
     */
    @SuppressWarnings("rawtypes")
    private Query dbCheckQuery(SearchUserDto userForm) throws ParseException {
        StringBuffer query = new StringBuffer(SELECT_USER_LIST_HQL);
        Query queryUserList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if (userForm != null) {
            if (userForm.getName() != null && !userForm.getName().isEmpty()) {
                query.append("AND u.name like :name ");
            }
            if (userForm.getEmail() != null && !userForm.getEmail().isEmpty()) {
                query.append("AND u.email = :email ");
            }
            if ((userForm.getFromDate() != null && !userForm.getFromDate().isEmpty()) && userForm.getToDate() != null
                    && !userForm.getToDate().isEmpty()) {
                query.append("AND u.createdAt BETWEEN :fromDate AND :toDate ");
            }
        }
        queryUserList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if (userForm != null) {
            if (userForm.getName() != null && !userForm.getName().isEmpty()) {
                queryUserList.setParameter("name", "%" + userForm.getName() + "%");
            }
            if (userForm.getEmail() != null && !userForm.getEmail().isEmpty()) {
                queryUserList.setParameter("email", userForm.getEmail());
            }
            if ((userForm.getFromDate() != null && !userForm.getFromDate().isEmpty()) && userForm.getToDate() != null
                    && !userForm.getToDate().isEmpty()) {
                Date fromDate = formatter.parse(userForm.getFromDate());
                Date toDate = formatter.parse(userForm.getToDate());
                queryUserList.setParameter("fromDate", fromDate);
                queryUserList.setParameter("toDate", toDate);
            }
        }
        return queryUserList;
    }
}