package ojt.scm.bulletin.web.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import ojt.scm.bulletin.persistence.entity.Post;

/**
 * <h2>PostForm Class</h2>
 * <p>
 * Process for Displaying PostForm
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */

public class PostForm {
    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private Integer id;

    /**
     * <h2>title</h2>
     * <p>
     * title
     * </p>
     */
    @NotEmpty
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    @NotEmpty
    private String description;

    /**
     * <h2>status</h2>
     * <p>
     * status
     * </p>
     */
    private Integer status;

    /**
     * <h2>createdUserID</h2>
     * <p>
     * createdUserID
     * </p>
     */
    private Integer createdUserID;

    /**
     * <h2>updatedUserID</h2>
     * <p>
     * updatedUserID
     * </p>
     */
    private Integer updatedUserID;

    /**
     * <h2>deletedUserID</h2>
     * <p>
     * deletedUserID
     * </p>
     */
    private Integer deletedUserID;

    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    private Date createdAt;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */

    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    private Date updatedAt;
    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */

    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    private Date deletedAt;

    /**
     * <h2>createdUserName</h2>
     * <p>
     * createdUserName
     * </p>
     */
    private String createdUserName;

    /**
     * <h2>getId</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * <h2>setId</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @return void
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <h2>getTitle</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * <h2>setTitle</h2>
     * <p>
     * 
     * </p>
     *
     * @param title
     * @return void
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * <h2>getDescription</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * <h2>setDescription</h2>
     * <p>
     * 
     * </p>
     *
     * @param description
     * @return void
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <h2>getStatus</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <h2>setStatus</h2>
     * <p>
     * 
     * </p>
     *
     * @param status
     * @return void
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <h2>getCreatedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getCreatedUserID() {
        return createdUserID;
    }

    /**
     * <h2>setCreatedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @param createdUserID
     * @return void
     */
    public void setCreatedUserID(Integer createdUserID) {
        this.createdUserID = createdUserID;
    }

    /**
     * <h2>getUpdatedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getUpdatedUserID() {
        return updatedUserID;
    }

    /**
     * <h2>setUpdatedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @param updatedUserID
     * @return void
     */
    public void setUpdatedUserID(Integer updatedUserID) {
        this.updatedUserID = updatedUserID;
    }

    /**
     * <h2>getCreatedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * <h2>getDeletedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getDeletedUserID() {
        return deletedUserID;
    }

    /**
     * <h2>setDeletedUserID</h2>
     * <p>
     * 
     * </p>
     *
     * @param deletedUserID
     * @return void
     */
    public void setDeletedUserID(Integer deletedUserID) {
        this.deletedUserID = deletedUserID;
    }

    /**
     * <h2>setCreatedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @param createdAt
     * @return void
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * <h2>getUpdatedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * <h2>setUpdatedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @param updatedAt
     * @return void
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * <h2>getDeletedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return Date
     */
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * <h2>setDeletedAt</h2>
     * <p>
     * 
     * </p>
     *
     * @param deletedAt
     * @return void
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * <h2>getCreatedUserName</h2>
     * <p>
     * Getter Method for Created User Name
     * </p>
     *
     * @return
     * @return String
     */
    public String getCreatedUserName() {
        return createdUserName;
    }

    /**
     * <h2>setCreatedUserName</h2>
     * <p>
     * Setter method for created user name
     * </p>
     *
     * @param createdUserName
     * @return void
     */
    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    /**
     * <h2>Constructor for PostForm</h2>
     * <p>
     * Constructor for PostForm
     * </p>
     */
    public PostForm() {
        super();
    }

    /**
     * <h2>Constructor for PostForm</h2>
     * <p>
     * Constructor for PostForm
     * </p>
     * 
     * @param post
     */
    public PostForm(Post post) {
        super();
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.status = post.getStatus();
        this.createdUserID = post.getCreatedUserID();
        this.updatedUserID = post.getUpdatedUserID();
        this.deletedUserID = post.getDeletedUserID();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.deletedAt = post.getDeletedAt();
        this.createdUserName = post.getCreatedUserName();
    }
}