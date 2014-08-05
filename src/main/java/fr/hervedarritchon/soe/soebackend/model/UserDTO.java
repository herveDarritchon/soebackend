/**
 *
 */
package fr.hervedarritchon.soe.soebackend.model;

/**
 * This class store information about the user received from the application
 * client side.
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class UserDTO {

	private String fullname;

	private String emailAddress;

	private String password;

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return this.fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(final String fullname) {
		this.fullname = fullname;
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

	/**
	 * @param fullname
	 * @param emailAddress
	 * @param password
	 */
	public UserDTO(final String fullname, final String emailAddress,
			final String password) {
		super();
		this.fullname = fullname;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	/**
	 *
	 */
	public UserDTO() {
		super();
	}

}
