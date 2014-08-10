/**
 *
 */
package fr.hervedarritchon.soe.soebackend.dao.model;

import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class UserDao {

	private String id;

	private String fullName;

	private String emailAddress;

	private String password;

	/**
	 *
	 * @param user
	 */
	public UserDao(final User user) {
		this.id = user.getId();
		this.fullName = user.getFullName();
		this.emailAddress = user.getEmailAddress();
		this.password = this.getPassword();
	}

	public User transformUserDaoToUser(final UserDao userDao) {
		return new User(userDao.getId(), userDao.getFullName(),
				userDao.getEmailAddress(), userDao.getPassword());
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

}
