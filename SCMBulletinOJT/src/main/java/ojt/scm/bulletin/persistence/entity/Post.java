package ojt.scm.bulletin.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ojt.scm.bulletin.web.form.PostForm;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Entity
@Table(name = "post")
public class Post implements Serializable {
    /**
     * <h2>serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * <h2>title</h2>
     * <p>
     * title
     * </p>
     */
    @Column(name = "title")
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    @Column(name = "description")
    private String description;

    /**
     * <h2>status</h2>
     * <p>
     * status
     * </p>
     */
    @Column(name = "status")
    private Integer status;

    /**
     * <h2>createdUserID</h2>
     * <p>
     * createdUserID
     * </p>
     */
    @Column(name = "created_user_id")
    private Integer createdUserID;

    /**
     * <h2>updatedUserID</h2>
     * <p>
     * updatedUserID
     * </p>
     */
    @Column(name = "updated_user_id")
    private Integer updatedUserID;

    /**
     * <h2>deletedUserID</h2>
     * <p>
     * deletedUserID
     * </p>
     */
    @Column(name = "deleted_user_id")
    private Integer deletedUserID;

    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    /**
     * <h2>createdUserName</h2>
     * <p>
     * createdUserName
     * </p>
     */
    @Column(name = "created_user_name")
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
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     */
    public Post() {
        super();
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
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param postForm
     */
    public Post(PostForm postForm) {
        this.id = postForm.getId();
        this.title = postForm.getTitle();
        this.status = postForm.getStatus();
        this.description = postForm.getDescription();
        this.createdUserID = postForm.getCreatedUserID();
        this.updatedUserID = postForm.getUpdatedUserID();
        this.deletedUserID = postForm.getDeletedUserID();
        this.createdAt = postForm.getCreatedAt();
        this.updatedAt = postForm.getUpdatedAt();
        this.deletedAt = postForm.getDeletedAt();
        this.createdUserName = postForm.getCreatedUserName();
    }

}