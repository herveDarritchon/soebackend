/**
 *
 */
package fr.hervedarritchon.soe.soebackend.api.model;

import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * This class store information about the user received from the application
 * client side.
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class UserDTO {

	private String id;

	private String fullName;

	private String emailAddress;

	private String password;

	/**
	 * @param fullname
	 * @param emailAddress
	 * @param password
	 */
	public UserDTO(final String fullname, final String emailAddress,
			final String password) {
		super();
		this.fullName = fullname;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	/**
	 *
	 */
	public UserDTO() {
		super();
	}

	/**
	 * Create a UserDTO from a User
	 * 
	 * @param user
	 */
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.fullName = user.getFullName();
		this.emailAddress = user.getEmailAddress();
		this.password = user.getPassword();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fullname
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullName(final String fullname) {
		this.fullName = fullname;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
