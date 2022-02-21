package ojt.scm.bulletin.bl.service.post;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ojt.scm.bulletin.bl.dto.post.PostDto;
import ojt.scm.bulletin.web.form.PostForm;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface PostService {
    /**
     * <h2>doSearchPostWithPagi</h2>
     * <p>
     * Getting list of post with pagination
     * </p>
     * 
     * @param currentPage    int
     * @param recordsPerPage int
     * @param postForm       PostForm
     * @param userForm       UserForm
     * @return
     */
    public List<PostDto> doSearchPostWithPagi(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm userForm);

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * to Get all the post List
     * </p>
     *
     * @param createdUserId int
     * @return
     * @return List<PostDto>
     */
    public List<PostDto> doGetPostList(int createdUserId);

    /**
     * <h2>doSearchPostList</h2>
     * <p>
     * To get list of post by search data
     * </p>
     *
     * @param createdUserId int
     * @param postForm      PostForm
     * @return
     * @return List<PostDto>
     */
    public List<PostDto> doSearchPostList(int createdUserId, PostForm postForm);

    /**
     * <h2>doGetPostbyId</h2>
     * <p>
     * Get post data by its Id
     * </p>
     *
     * @param postId int
     * @return
     * @return PostForm
     */
    public PostForm doGetPostbyId(int postId);

    /**
     * <h2>isTitleExist</h2>
     * <p>
     * to check if Title already exist
     * </p>
     *
     * @param title String
     * @return
     * @return boolean
     */
    public boolean isTitleExist(String title);

    /**
     * <h2>isUpdateTitleExist</h2>
     * <p>
     * To check if updated title already exists
     * </p>
     *
     * @param title  String
     * @param postId int
     * @return
     * @return boolean
     */
    public boolean isUpdateTitleExist(String title, int postId);

    /**
     * <h2>doCreatePost</h2>
     * <p>
     * To Create Post
     * </p>
     *
     * @param postForm      PostForm
     * @param createdUserID int
     * @return void
     */
    public void doCreatePost(PostForm postForm, int createdUserID);

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * to Update Post
     * </p>
     *
     * @param postForm    PostForm
     * @param currentUser UserForm
     * @return void
     */
    public void doUpdatePost(PostForm postForm, UserForm currentUser);

    /**
     * <h2>doDeletePost</h2>
     * <p>
     * to Delete Post
     * </p>
     *
     * @param postId        int
     * @param deletedUserID int
     * @return void
     */
    public void doDeletePost(int postId, int deletedUserID);

    /**
     * <h2>doUploadCSV</h2>
     * <p>
     * To upload File
     * </p>
     *
     * @param fileUpload    MultipartFile
     * @param currentUserId int
     * @return
     * @throws IOException
     * @return List<String>
     */
    public List<String> doUploadCSV(MultipartFile fileUpload, int currentUserId) throws IOException;
}