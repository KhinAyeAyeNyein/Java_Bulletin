package ojt.scm.bulletin.persistence.dao.post;

import java.util.Date;
import java.util.List;

import ojt.scm.bulletin.persistence.entity.Post;
import ojt.scm.bulletin.persistence.entity.User;
import ojt.scm.bulletin.web.form.PostForm;
import ojt.scm.bulletin.web.form.UserForm;

public interface PostDao {
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
    public List<Post> dbGetPostListPagi(int currentPage, int noOfPost, PostForm postForm, UserForm userForm);

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
    public List<Post> dbGetPostList(User user);

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
    public List<Post> dbSearchPostList(UserForm user, PostForm postForm);

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
    public Post dbGetPostbyID(int postId);

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
    public Post dbGetPostbyTitle(String title);

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
    public List<Post> dbUpdatedPostExistList(String title);

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
    public void dbCreatePost(Post post, int createdUserID, Date currentDate);

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update post to Database
     * </p>
     *
     * @param post Post
     * @return void
     */
    public void dbUpdatePost(Post post);

    /**
     * <h2>dbPostUploadData</h2>
     * <p>
     * Saving Post List getting form CSV file
     * </p>
     *
     * @param post
     * @return void
     */
    public void dbPostUploadData(Post post);
}