/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.model;

/**
 * User Class Used to store data about the user.
 *  
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class User {

	private String id;

	private String fullName;

	private String emailAddress;

	private String password;

	private boolean authenticated;

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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the authenticated
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/**
	 * @param authenticated
	 *            the authenticated to set
	 */
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	/**
	 * @param fullName
	 * @param emailAddress
	 * @param password
	 */
	public User(String fullName, String emailAddress, String password) {
		super();
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

}
