package ojt.scm.bulletin.web.controller.post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ojt.scm.bulletin.bl.dto.post.PostDto;
import ojt.scm.bulletin.bl.service.post.PostService;
import ojt.scm.bulletin.bl.service.post.ReportService;
import ojt.scm.bulletin.bl.service.user.UserService;
import ojt.scm.bulletin.web.form.PostForm;
import ojt.scm.bulletin.web.form.UserForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Controller
public class PostController {
    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

    /**
     * <h2>reportService</h2>
     * <p>
     * reportService
     * </p>
     */
    @Autowired
    private ReportService reportService;

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
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>getPostList</h2>
     * <p>
     * Getting list of post
     * </p>
     *
     * @param request  HttpServletRequest
     * @param postForm PostForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/postList", method = RequestMethod.GET)
    public ModelAndView getPostList(HttpServletRequest request, PostForm postForm) throws IOException {
        ModelAndView postListView = new ModelAndView("postList");
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(postListView, currentPage, recordsPerPage, false, postForm, loginUserId);
        return postListView;
    }

    /**
     * <h2>detailPost</h2>
     * <p>
     * Showing Detail of Post
     * </p>
     *
     * @param postId int
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/detailPost", method = RequestMethod.GET)
    public ModelAndView detailPost(@RequestParam("id") Integer postId) {
        ModelAndView postView = new ModelAndView("detailPost");
        PostForm detailPost = null;
        try {
            detailPost = this.postService.doGetPostbyId(postId);
        } catch (NullPointerException e) {
            postView = new ModelAndView("redirect:/error500");
        }
        postView.addObject("detailPost", detailPost);
        return postView;
    }

    /**
     * <h2>createPost</h2>
     * <p>
     * show create Post view
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public ModelAndView createPost() {
        ModelAndView postCreateView = new ModelAndView("createPost");
        postCreateView.addObject("createPost", new PostForm());
        return postCreateView;
    }

    /**
     * <h2>createPost</h2>
     * <p>
     * show create confirm view if data is valid
     * </p>
     *
     * @param postForm PostForm
     * @param result   BindingResult
     * @param request  HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPostConfirm", params = "confirmPost", method = RequestMethod.POST)
    public ModelAndView createPost(@ModelAttribute("createPost") @Valid PostForm postForm, BindingResult result,
            HttpServletRequest request) {
        ModelAndView postConfirmView = new ModelAndView("createPost");
        if (result.hasErrors()) {
            postConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return postConfirmView;
        }
        if (this.postService.isTitleExist(postForm.getTitle())) {
            postConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            return postConfirmView;
        }
        postConfirmView = new ModelAndView("createPostConfirm");
        postConfirmView.addObject("postForm", postForm);
        return postConfirmView;
    }

    /**
     * <h2>confirmCreatePost</h2>
     * <p>
     * Insert new post
     * </p>
     *
     * @param postForm PostForm
     * @param request  HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertPost", params = "addPost", method = RequestMethod.POST)
    public ModelAndView confirmPostConfirm(@ModelAttribute("postForm") @Valid PostForm postForm,
            HttpServletRequest request) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        this.postService.doCreatePost(postForm, loginUserId);
        ModelAndView createPostView = new ModelAndView("redirect:/postList");
        return createPostView;
    }

    /**
     * <h2>cancelPostConfirm</h2>
     * <p>
     * Cancel create post
     * </p>
     *
     * @param postForm PostForm
     * @param result   HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelPostConfirm(@ModelAttribute("createPost") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView createPostView = new ModelAndView("createPost");
        createPostView.addObject("createPost", postForm);
        return createPostView;
    }

    /**
     * <h2>editPost</h2>
     * <p>
     * Edit Post View
     * </p>
     *
     * @param postId int
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePost", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam("id") Integer postId) {
        ModelAndView editView = new ModelAndView("updatePost");
        PostForm postForm = null;
        try {
            postForm = this.postService.doGetPostbyId(postId);
        } catch (NullPointerException e) {
            editView = new ModelAndView("redirect:/error500");
        }
        editView.addObject("editPostForm", postForm);
        return editView;
    }

    /**
     * <h2>editPost</h2>
     * <p>
     * Edit post confirm view
     * </p>
     *
     * @param postForm PostForm
     * @param result   BindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePostConfirm", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("editPostForm") @Valid PostForm postForm, BindingResult result) {
        ModelAndView updateConfirmView = new ModelAndView("updatePost");
        Boolean isPostTitleExist = this.postService.isUpdateTitleExist(postForm.getTitle(), postForm.getId());
        if (result.hasErrors()) {
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
            return updateConfirmView;
        }
        if (isPostTitleExist) {
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            return updateConfirmView;
        }
        updateConfirmView = new ModelAndView("updatePostConfirm");
        postForm.setStatus(1);
        if (postForm.getStatus() == null) {
            postForm.setStatus(0);
        }
        updateConfirmView.addObject("updatePostForm", postForm);
        return updateConfirmView;
    }

    /**
     * <h2>confirmUpdatePost</h2>
     * <p>
     * Update post in post list
     * </p>
     *
     * @param postForm PostForm
     * @param request  HttpServletRequest
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editPost", params = "update", method = RequestMethod.POST)
    public ModelAndView confirmUpdatePost(@ModelAttribute("updatePostForm") @Valid PostForm postForm,
            HttpServletRequest request) throws IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        UserForm loginUser = this.userService.doGetUserbyId(loginUserId);
        this.postService.doUpdatePost(postForm, loginUser);
        ModelAndView updatePostView = new ModelAndView("redirect:/postList");
        return updatePostView;
    }

    /**
     * <h2>cancelUpdatePost</h2>
     * <p>
     * Cancel update post
     * </p>
     *
     * @param postForm PostForm
     * @param result   BindingResult
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdatePost(@ModelAttribute("editPostForm") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView updatPostView = new ModelAndView("updatePost");
        updatPostView.addObject("editPostForm", postForm);
        return updatPostView;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Deleting post
     * </p>
     *
     * @param postId  int
     * @param request HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public ModelAndView deletePost(@RequestParam("id") Integer postId, HttpServletRequest request) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        ModelAndView view = new ModelAndView();
        this.postService.doDeletePost(postId, loginUserId);
        this.getPagination(view, 1, 10, false, new PostForm(), loginUserId);
        view.setViewName("redirect:/postList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0019", null, null));
        return view;
    }

    /**
     * <h2>searchPost</h2>
     * <p>
     * Showing search post list
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ModelAndView searchPost(HttpServletRequest request) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        PostForm postForm = new PostForm();
        String search_input = request.getParameter("search_input");
        ModelAndView postListView = new ModelAndView("postList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        if (search_input.isEmpty()) {
            this.getPagination(postListView, currentPage, recordsPerPage, false, postForm, loginUserId);
        } else {
            postForm.setTitle(search_input);
            postForm.setDescription(search_input);
            this.getPagination(postListView, currentPage, recordsPerPage, true, postForm, loginUserId);
            postListView.addObject("searchData", search_input);
        }
        return postListView;
    }

    /**
     * <h2>searchPost</h2>
     * <p>
     * Searching post
     * </p>
     *
     * @param search_input String
     * @param request      HttpServletRequest
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchPost", params = "searchPost", method = RequestMethod.POST)
    public ModelAndView searchPost(@RequestParam("search-input") String search_input, HttpServletRequest request) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        PostForm postForm = new PostForm();
        ModelAndView postListView = new ModelAndView("postList");
        if (search_input.isEmpty()) {
            this.getPagination(postListView, 1, 10, false, postForm, loginUserId);
        } else {
            postForm.setTitle(search_input);
            postForm.setDescription(search_input);
            postForm.setCreatedUserName(search_input);
            this.getPagination(postListView, 1, 10, true, postForm, loginUserId);
            postListView.addObject("searchData", search_input);
        }
        return postListView;
    }

    /**
     * <h2>downloadExcel</h2>
     * <p>
     * Download Excel
     * </p>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return
     * @throws IOException
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping(value = "/searchPost", params = "downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        List<PostDto> postListview = this.postService.doGetPostList(loginUserId);
        try {
            this.reportService.downloadExcel(postListview);
        } catch (IOException | ParseException e) {
            Log.error(e);
        }
        ModelAndView postView = new ModelAndView();
        postView.setViewName("redirect:/postList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0021", null, null));
        return postView;
    }

    /**
     * <h2>uploadCSV</h2>
     * <p>
     * Upload CSV view
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadCSV", method = RequestMethod.GET)
    public ModelAndView uploadCSV() {
        ModelAndView uploadView = new ModelAndView("uploadCSV");
        return uploadView;
    }

    /**
     * <h2>uploadCSV</h2>
     * <p>
     * Upload CSV file to post list
     * </p>
     *
     * @param fileUpload MultipartFile
     * @param request    HttpServletRequest
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadCSV", method = RequestMethod.POST)
    public ModelAndView uploadCSV(@RequestParam("fileUpload") MultipartFile fileUpload, HttpServletRequest request) {
        ModelAndView uploadView = new ModelAndView("uploadCSV");
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        // Show Error Message when no file is chosen
        if (fileUpload.getSize() == 0) {
            uploadView.addObject("uploadErrorMsg", messageSource.getMessage("M_SC_0020", null, null));
            return uploadView;
        }
        List<String> uploadError = null;
        try {
            uploadError = this.postService.doUploadCSV(fileUpload, loginUserId);
        } catch (IOException e) {
            Log.error(e);
        }
        // if there is no Error, read csv file and show in postList
        if (uploadError.size() > 0) {
            uploadView.addObject("uploadErrorMsg", uploadError);
            return uploadView;
        }
        uploadView = new ModelAndView("redirect:/postList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0027", null, null));
        return uploadView;
    }

    /**
     * <h2>getPagination</h2>
     * <p>
     * Pagination for post List
     * </p>
     *
     * @param postListView   ModelAndView
     * @param currentPage    int
     * @param recordsPerPage int
     * @param resultSearch   boolea
     * @param postForm       PostForm
     * @param userForm       UserForm
     * @return
     * @throws IOException
     * @return void
     */
    private void getPagination(ModelAndView postListView, int currentPage, int recordsPerPage, boolean resultSearch,
            PostForm postForm, int postCreatedUserID) {
        List<PostDto> postList;
        if (resultSearch == false) {
            postList = this.postService.doGetPostList(postCreatedUserID);
        } else {
            postList = this.postService.doSearchPostList(postCreatedUserID, postForm);
        }
        UserForm loginedUser = null;
        try {
            loginedUser = this.userService.doGetUserbyId(postCreatedUserID);
        } catch (IOException e) {
            postListView = new ModelAndView("redirect:/error500");
        }
        int rows = postList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        List<PostDto> searchPostList = this.postService.doSearchPostWithPagi(currentPage, recordsPerPage, postForm,
                loginedUser);
        postListView.addObject("noOfPages", nOfPages);
        postListView.addObject("currentPage", currentPage);
        postListView.addObject("recordsPerPage", recordsPerPage);
        postListView.addObject("postList", searchPostList);
    }

    /**
     * <h2>getCurrentPage</h2>
     * <p>
     * To get current page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getCurrentPage(HttpServletRequest request) {
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        return currentPage;
    }

    /**
     * <h2>getRecordsPerPage</h2>
     * <p>
     * To get records per page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getRecordsPerPage(HttpServletRequest request) {
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 10;
        return recordsPerPage;
    }
}