package ojt.scm.bulletin.persistence.dao.post.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.scm.bulletin.persistence.dao.post.PostDao;
import ojt.scm.bulletin.persistence.dao.user.UserDao;
import ojt.scm.bulletin.persistence.entity.Post;
import ojt.scm.bulletin.persistence.entity.User;
import ojt.scm.bulletin.web.form.PostForm;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>PostDaoImpl Class</h2>
 * <p>
 * Process for Displaying PostDaoImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Repository
@SuppressWarnings("deprecation")
public class PostDaoImpl implements PostDao {
    /**
     * <h2>SELECT_POST_LIST_HQL</h2>
     * <p>
     * SELECT_POST_LIST_HQL
     * </p>
     */
    public static String SELECT_POST_LIST_HQL = "FROM Post p "
                                              + "WHERE p.deletedAt IS NUll ";

    /**
     * <h2>SELECT_POST_BY_ID_HQL</h2>
     * <p>
     * SELECT_POST_BY_ID_HQL
     * </p>
     */
    public static String SELECT_POST_BY_ID_HQL = "FROM Post p "
                                               + "WHERE p.id = :id ";

    /**
     * <h2>SELECT_POST_BY_TITLE_HQL</h2>
     * <p>
     * SELECT_POST_BY_TITLE_HQL
     * </p>
     */
    public static String SELECT_POST_BY_TITLE_HQL = "FROM Post p "
                                                  + "WHERE p.title = :title ";
    
    /**
     * <h2>SELECT_POST_BY_SEARCH_DATA</h2>
     * <p>
     * SELECT_POST_BY_SEARCH_DATA
     * </p>
     */
    public static String SELECT_POST_BY_SEARCH_DATA = "AND (p.title like :title "
                                                    + "OR p.description like :description "
                                                    + "OR p.createdUserName like :name) ";

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>dbGetPostListPagi</h2>
     * <p>
     * Getting List of Post with Pagination
     * </p>
     *
     * @param currentPage int
     * @param noOfPost    int
     * @param postForm    PostForm
     * @param userForm    UserForm
     * @return
     * @return List<Post>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Post> dbGetPostListPagi(int currentPage, int noOfPost, PostForm postForm, UserForm userForm) {
        int start = currentPage * noOfPost - noOfPost;
        Query queryPostList = dbCreateQueryList(userForm, postForm);
        queryPostList.setFirstResult(start);
        queryPostList.setMaxResults(noOfPost);
        List<Post> postList = (List<Post>) queryPostList.list();
        return postList;
    }

    /**
     * <h2>dbGetPostList</h2>
     * <p>
     * Getting the Post List with Specific Condition
     * </p>
     *
     * @param user User
     * @return
     * @return List<Post>
     */
    @Override
    public List<Post> dbGetPostList(User user) {
        StringBuffer query = new StringBuffer(SELECT_POST_LIST_HQL);
        if ((user.getType()).equals("1")) {
            query.append("AND p.createdUserID = :postCreatedUserID ");
        } else if (user.getType().equals("0")) {
            query.append(
                    "AND (NOT(p.status = :status OR p.createdUserID = :currentUserID) OR p.createdUserID = :currentUserID) ");
        }
        @SuppressWarnings("rawtypes")
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if ((user.getType()).equals("1")) {
            queryPostList.setParameter("postCreatedUserID", user.getId());
        } else if (user.getType().equals("0")) {
            queryPostList.setParameter("status", 1);
            queryPostList.setParameter("currentUserID", user.getId());
        }
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPostList.list();
        return postList;
    }

    /**
     * <h2>dbSearchPostList</h2>
     * <p>
     * Getting the List of Post with Search Result
     * </p>
     *
     * @param userForm UserForm
     * @param postForm PostForm
     * @return
     * @return List<Post>
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<Post> dbSearchPostList(UserForm userForm, PostForm postForm) {
        Query queryPostList = dbCreateQueryList(userForm, postForm);
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPostList.list();
        return postList;
    }

    /**
     * <h2>dbGetPostbyID</h2>
     * <p>
     * Get post by post ID
     * </p>
     *
     * @param postId int
     * @return
     * @return Post
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Post dbGetPostbyID(int postId) {
        Query queryPostById = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_ID_HQL);
        queryPostById.setParameter("id", postId);
        Post resultPost = (Post) queryPostById.uniqueResult();
        return resultPost;
    }

    /**
     * <h2>dbGetPostbyTitle</h2>
     * <p>
     * To check Title is Unique or not
     * </p>
     *
     * @param title String
     * @return
     * @return Post
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Post dbGetPostbyTitle(String title) {
        Query queryPostByTitle = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_TITLE_HQL);
        queryPostByTitle.setParameter("title", title);
        Post resultPost = (Post) queryPostByTitle.uniqueResult();
        return resultPost;
    }

    /**
     * <h2>dbUpdatedPostExistList</h2>
     * <p>
     * Getting the Title of Post not to duplicate
     * </p>
     *
     * @param title String
     * @return
     * @return List<Post>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Post> dbUpdatedPostExistList(String title) {
        Query queryPost = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_TITLE_HQL);
        queryPost.setParameter("title", title);
        List<Post> postList = (List<Post>) queryPost.list();
        return postList;
    }

    /**
     * <h2>dbCreatePost</h2>
     * <p>
     * Insert new post into Database
     * </p>
     *
     * @param post          Post
     * @param createdUserID int
     * @param currentDate   Date
     * @return void
     */
    @Override
    public void dbCreatePost(Post post, int createdUserID, Date currentDate) {
        post.setStatus(1);
        post.setCreatedAt(currentDate);
        post.setCreatedUserID(createdUserID);
        String name = userDao.dbGetNameByID(post.getCreatedUserID());
        post.setCreatedUserName(name);
        this.sessionFactory.getCurrentSession().save(post);
    }

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update post to Database
     * </p>
     *
     * @param post Post
     * @return void
     */
    @Override
    public void dbUpdatePost(Post post) {
        this.sessionFactory.getCurrentSession().update(post);
    }

    /**
     * <h2>dbPostUploadData</h2>
     * <p>
     * Saving Post List getting form CSV file
     * </p>
     *
     * @param post
     * @return void
     */
    @Override
    public void dbPostUploadData(Post post) {
        this.sessionFactory.getCurrentSession().save(post);
    }

    /**
     * <h2>dbCreateQueryList</h2>
     * <p>
     * Method to call query list
     * </p>
     *
     * @param userForm UserForm
     * @param postForm PostForm
     * @return
     * @return Query
     */
    @SuppressWarnings("rawtypes")
    private Query dbCreateQueryList(UserForm userForm, PostForm postForm) {
        StringBuffer query = new StringBuffer(SELECT_POST_LIST_HQL);
        if (userForm.getType().equals("1")) {
            query.append("AND p.createdUserID = :currentUserID ");
        } else if (userForm.getType().equals("0")) {
            query.append(
                    "AND (NOT(p.status = :status OR p.createdUserID = :currentUserID) OR p.createdUserID = :currentUserID) ");
        }
        if (postForm != null && (postForm.getTitle() != null || postForm.getDescription() != null
                || postForm.getCreatedUserName() != null)) {
            query.append(SELECT_POST_BY_SEARCH_DATA);
        }
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if (postForm != null && (postForm.getTitle() != null || postForm.getDescription() != null
                || postForm.getCreatedUserName() != null)) {
            queryPostList.setParameter("title", "%" + postForm.getTitle() + "%");
            queryPostList.setParameter("description", "%" + postForm.getDescription() + "%");
            queryPostList.setParameter("name", "%" + postForm.getCreatedUserName() + "%");
        }
        if (userForm.getType().equals("1")) {
            queryPostList.setParameter("currentUserID", userForm.getId());
            return queryPostList;
        }
        queryPostList.setParameter("status", 1);
        queryPostList.setParameter("currentUserID", userForm.getId());
        return queryPostList;
    }
}