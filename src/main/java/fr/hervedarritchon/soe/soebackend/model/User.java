/**
 *
 */
package fr.hervedarritchon.soe.soebackend.model;

import fr.hervedarritchon.soe.soebackend.api.model.UserDto;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;

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

	public User() {
		super();
	}

	/**
	 * @param authenticated
	 *            the authenticated to set
	 */
	public void setAuthenticated(final boolean authenticated) {
		this.authenticated = authenticated;
	}

	/**
	 * @param fullName
	 * @param emailAddress
	 * @param password
	 */
	public User(final String id, final String fullName,
			final String emailAddress, final String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public User(final UserDto userDTO) throws InvalidParameterException {
		super();

		if ((userDTO.getFullName() == null) || userDTO.getFullName().isEmpty()) {
			throw new InvalidParameterException("Fullname");
		} else {
			this.fullName = userDTO.getFullName();
		}
		if ((userDTO.getEmailAddress() == null)
				|| userDTO.getEmailAddress().isEmpty()) {
			throw new InvalidParameterException("EmailAdress");
		} else {
			this.emailAddress = userDTO.getEmailAddress();
		}
		if ((userDTO.getPassword() == null) || userDTO.getPassword().isEmpty()) {
			throw new InvalidParameterException("Password");
		} else {
			this.password = userDTO.getPassword();
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(final String fullName) {
		this.fullName = fullName;
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
	 * @return the authenticated
	 */
	public boolean isAuthenticated() {
		return this.authenticated;
	}

}
