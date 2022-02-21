package ojt.scm.bulletin.bl.dto.user;

import ojt.scm.bulletin.persistence.entity.User;

/**
 * <h2>SearchUserDto Class</h2>
 * <p>
 * Process for Displaying SearchUserDto
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public class SearchUserDto {
    /**
     * <h2>name</h2>
     * <p>
     * name
     * </p>
     */
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    private String email;

    /**
     * <h2>fromDate</h2>
     * <p>
     * fromDate
     * </p>
     */
    private String fromDate;

    /**
     * <h2>toDate</h2>
     * <p>
     * toDate
     * </p>
     */
    private String toDate;

    /**
     * <h2>CreatedUserId</h2>
     * <p>
     * CreatedUserId
     * </p>
     */
    private Integer CreatedUserId;

    /**
     * <h2>getName</h2>
     * <p>
     * Getter method for naem
     * </p>
     *
     * @return
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * <h2>setName</h2>
     * <p>
     * Setter method for name
     * </p>
     *
     * @param name
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h2>getEmail</h2>
     * <p>
     * Getter method for email
     * </p>
     *
     * @return
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * <h2>setEmail</h2>
     * <p>
     * Setter method for email
     * </p>
     *
     * @param email
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>getFromDate</h2>
     * <p>
     * Getter method for fromDate
     * </p>
     *
     * @return
     * @return Date
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * <h2>setFromDate</h2>
     * <p>
     * Setter method for fromDate
     * </p>
     *
     * @param fromDate
     * @return void
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * <h2>getToDate</h2>
     * <p>
     * Getter method for toDate
     * </p>
     *
     * @return
     * @return Date
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * <h2>setToDate</h2>
     * <p>
     * Setter method for toDate
     * </p>
     *
     * @param toDate
     * @return void
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * <h2>Constructor for SearchUserDto</h2>
     * <p>
     * Constructor for SearchUserDto
     * </p>
     */
    public SearchUserDto() {
        super();
    }

    /**
     * <h2>getCreatedUserId</h2>
     * <p>
     * Getter Method for CreatdUserId
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getCreatedUserId() {
        return CreatedUserId;
    }

    /**
     * <h2>setCreatedUserId</h2>
     * <p>
     * Setter Method for CreatedUserId
     * </p>
     *
     * @param createdUserId
     * @return void
     */
    public void setCreatedUserId(Integer createdUserId) {
        CreatedUserId = createdUserId;
    }

    /**
     * <h2>Constructor for SearchUserDto</h2>
     * <p>
     * Constructor for SearchUserDto
     * </p>
     * 
     * @param user
     */
    public SearchUserDto(User user) {
        super();
        this.name = user.getName();
        this.email = user.getEmail();
        this.fromDate = user.getCreatedAt().toString();
        this.toDate = user.getCreatedAt().toString();
        this.CreatedUserId = user.getCreatedUserId();
    }
}