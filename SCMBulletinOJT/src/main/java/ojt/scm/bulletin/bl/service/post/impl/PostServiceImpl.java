package ojt.scm.bulletin.bl.service.post.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ojt.scm.bulletin.bl.dto.post.PostDto;
import ojt.scm.bulletin.bl.service.post.PostService;
import ojt.scm.bulletin.persistence.dao.post.PostDao;
import ojt.scm.bulletin.persistence.dao.user.UserDao;
import ojt.scm.bulletin.persistence.entity.Post;
import ojt.scm.bulletin.persistence.entity.User;
import ojt.scm.bulletin.web.form.PostForm;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class PostServiceImpl implements PostService {
    /**
     * <h2>postDao</h2>
     * <p>
     * postDao
     * </p>
     */
    @Autowired
    private PostDao postDao;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

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
    @Override
    public List<PostDto> doSearchPostWithPagi(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm userForm) {
        List<PostDto> postDtoList = new ArrayList<PostDto>();
        List<Post> postList = this.postDao.dbGetPostListPagi(currentPage, recordsPerPage, postForm, userForm);
        for (Post post : postList) {
            PostDto postDto = new PostDto(post);
            String name = userDao.dbGetNameByID(postDto.getCreatedUserID());
            postDto.setCreatedUserName(name);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

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
    @Override
    public List<PostDto> doGetPostList(int createdUserId) {
        User user = this.userDao.dbGetUserbyID(createdUserId);
        List<Post> postList = (List<Post>) this.postDao.dbGetPostList(user);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostDto Entity2Dto = new PostDto(post);
            postDtoList.add(Entity2Dto);
        }
        return postDtoList;
    }

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
    @Override
    public List<PostDto> doSearchPostList(int createdUserId, PostForm postForm) {
        User user = this.userDao.dbGetUserbyID(createdUserId);
        UserForm userForm = new UserForm(user);
        List<Post> postList = (List<Post>) this.postDao.dbSearchPostList(userForm, postForm);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostDto Entity2Dto = new PostDto(post);
            postDtoList.add(Entity2Dto);
        }
        return postDtoList;
    }

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
    @Override
    public PostForm doGetPostbyId(int postId) {
        Post resultPost = this.postDao.dbGetPostbyID(postId);
        PostForm resultPostform = resultPost != null ? new PostForm(resultPost) : null;
        if (resultPostform == null || resultPostform.equals(null)) {
            throw new NullPointerException();
        }
        return resultPostform;
    }

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
    @Override
    public boolean isTitleExist(String title) {
        Post resultPost = this.postDao.dbGetPostbyTitle(title);
        boolean titleExist = false;
        if (resultPost != null) {
            titleExist = true;
        }
        return titleExist;
    }

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
    @Override
    public boolean isUpdateTitleExist(String title, int postId) {
        boolean updateTitleExist = false;
        List<Post> postList = postDao.dbUpdatedPostExistList(title);
        Post postById = postDao.dbGetPostbyID(postId);
        for (Post post : postList) {
            if (post.getTitle() != postById.getTitle()) {
                updateTitleExist = true;
            }
        }
        return updateTitleExist;
    }

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
    @Override
    public void doCreatePost(PostForm postForm, int createdUserID) {
        Post post = new Post(postForm);
        this.postDao.dbCreatePost(post, createdUserID, new Date());
    }

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
    @Override
    public void doUpdatePost(PostForm postForm, UserForm currentUser) {
        Post post = new Post(postForm);
        Date currentDate = new Date();
        Post updatePost = this.postDao.dbGetPostbyID(post.getId());
        Post postByTitle = this.postDao.dbGetPostbyTitle(post.getTitle());
        if (updatePost != null) {
            if (postByTitle == null || (postByTitle != null && postByTitle.getTitle() == updatePost.getTitle())) {
                updatePost.setTitle(post.getTitle());
                updatePost.setDescription(post.getDescription());
                updatePost.setStatus(post.getStatus());
                updatePost.setUpdatedAt(currentDate);
                updatePost.setUpdatedUserID(currentUser.getId());
            }
            this.postDao.dbUpdatePost(updatePost);
        }
    }

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
    @Override
    public void doDeletePost(int postId, int deletedUserID) {
        Post deletePost = this.postDao.dbGetPostbyID(postId);
        deletePost.setDeletedAt(new Date());
        deletePost.setDeletedUserID(deletedUserID);
    }

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
    @SuppressWarnings("null")
    @Override
    public List<String> doUploadCSV(MultipartFile fileUpload, int currentUserId) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileUpload.getInputStream()));
        List<String> uploadError = new ArrayList<>();
        int row = 0;
        String[] col;
        String errorMsgs;
        List<Post> postList = new ArrayList<Post>();
        String line = fileReader.readLine();
        while (line != null) {
            row++;
            Post post = new Post();
            col = line.split(",");
            // Check if there is data in csv file
            if (col != null || col.length <= 1) {
                // if there is no data at all in csv file, show error msgs
                if (col.length == 1 && col[0].equals("")) {
                    errorMsgs = messageSource.getMessage("M_SC_0026", null, null);
                    uploadError.add(errorMsgs);
                    return uploadError;
                }
                // if description or status is missing, show error msgs
                if (col.length == 2 || col.length == 1) {
                    errorMsgs = messageSource.getMessage("M_SC_0024", null, null);
                    uploadError.add(errorMsgs + row);
                    return uploadError;
                }
                // Check if there is enough data to take as input for post
                if (col.length >= 3) {
                    Post isTitleExit = this.postDao.dbGetPostbyTitle(col[0]);
                    if (isTitleExit != null) { // if title is already exit, show error msgs
                        errorMsgs = messageSource.getMessage("M_SC_0005", null, null);
                        uploadError.add(errorMsgs + row);
                        return uploadError;
                    }
                    // If title does not exit in import data, show error msgs
                    if (col[0].equals("") || col[0].equals(null) || col[0].isEmpty()) {
                        errorMsgs = messageSource.getMessage("M_SC_0006", null, null);
                        uploadError.add(errorMsgs + row);
                        return uploadError;
                    }
                    // if status data is not correct, show error msgs
                    if (col[2].length() > 1) {
                        errorMsgs = messageSource.getMessage("M_SC_0025", null, null);
                        uploadError.add(errorMsgs + row);
                        return uploadError;
                    }
                    // if data in description is null, show error msgs
                    if (col[1].equals(null) || col[1].equals("")) {
                        errorMsgs = messageSource.getMessage("M_SC_0024", null, null);
                        uploadError.add(errorMsgs + row);
                        return uploadError;
                    }
                    // if there is some data and Title of import data is unique, add data
                    if (!col[0].equals("") && !col[0].equals(null) && !col[0].isEmpty() && isTitleExit == null
                            && col[2].length() == 1) {
                        post.setTitle(col[0]);
                        post.setDescription(col[1]);
                        post.setStatus((!col[2].equals("0") && !col[2].equals("1")) ? Integer.parseInt("1")
                                : Integer.parseInt(col[2]));
                        post.setCreatedAt(new Date());
                        post.setCreatedUserID(currentUserId);
                        String name = userDao.dbGetNameByID(post.getCreatedUserID());
                        post.setCreatedUserName(name);
                        postList.add(post);
                    }
                }
                line = fileReader.readLine();
            }
        }
        fileReader.close();
        for (Post postData : postList) {
            this.postDao.dbPostUploadData(postData);
        }
        return uploadError;
    }
}