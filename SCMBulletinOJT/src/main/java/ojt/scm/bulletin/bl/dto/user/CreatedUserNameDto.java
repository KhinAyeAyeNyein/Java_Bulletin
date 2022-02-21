package ojt.scm.bulletin.bl.dto.user;

import ojt.scm.bulletin.persistence.entity.User;

/**
 * <h2>CreatedUserNameDto Class</h2>
 * <p>
 * Process for Displaying CreatedUserNameDto
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public class CreatedUserNameDto {
    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private Integer id;

    /**
     * <h2>name</h2>
     * <p>
     * name
     * </p>
     */
    private String name;

    /**
     * <h2>createdUserId</h2>
     * <p>
     * createdUserId
     * </p>
     */
    private Integer createdUserId;

    /**
     * <h2>getId</h2>
     * <p>
     * Getter Method for Id
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
     * Setter Method for ID
     * </p>
     *
     * @param id
     * @return void
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <h2>getName</h2>
     * <p>
     * Getter method for name
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
     * <h2>getCreatedUserId</h2>
     * <p>
     * Getter method for CreateduserId
     * </p>
     *
     * @return
     * @return Integer
     */
    public Integer getCreatedUserId() {
        return createdUserId;
    }

    /**
     * <h2>setCreatedUserId</h2>
     * <p>
     * Setter method for CretedUserId
     * </p>
     *
     * @param createdUserId
     * @return void
     */
    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * <h2>Constructor for CreatedUserNameDto</h2>
     * <p>
     * Constructor for CreatedUserNameDto
     * </p>
     */
    public CreatedUserNameDto() {
        super();
    }

    /**
     * <h2>Constructor for CreatedUserNameDto</h2>
     * <p>
     * Constructor for CreatedUserNameDto
     * </p>
     * 
     * @param user
     */
    public CreatedUserNameDto(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.createdUserId = user.getCreatedUserId();
    }
}