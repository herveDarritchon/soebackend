/**
 *
 */
package fr.hervedarritchon.soe.soebackend;

import fr.hervedarritchon.soe.soebackend.api.model.UserDTO;
import fr.hervedarritchon.soe.soebackend.dao.StorageDao;
import fr.hervedarritchon.soe.soebackend.exception.AuthenticateUserException;
import fr.hervedarritchon.soe.soebackend.exception.CannotCreateExceptionException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.UpdateUserException;
import fr.hervedarritchon.soe.soebackend.exception.UserAlreadyExistException;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 *
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class UserManager {

	private User user;

	private final StorageDao dao;

	/**
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * @return the dao
	 */
	public StorageDao getDao() {
		return this.dao;
	}

	/**
	 * @param dao
	 * @param user
	 */
	public UserManager(final StorageDao dao, final User user) {
		super();
		this.user = user;
		this.dao = dao;
	}

	public UserManager() {
		this.dao = new StorageDao();
		this.user = null;
	}

	/**
	 * Check that all the mandatory parameters passed are valid (not null and
	 * not empty)
	 *
	 * @param fullName
	 * @param emailAddress
	 * @param password
	 * @throws InvalidParameterException
	 */
	public void checkUserValueAreValid(final String fullName,
			final String emailAddress, final String password)
					throws InvalidParameterException {
		if ((fullName == null) || fullName.isEmpty()) {
			throw new InvalidParameterException("Fullname");
		}
		if ((emailAddress == null) || emailAddress.isEmpty()) {
			throw new InvalidParameterException("EmailAdress");
		}
		if ((password == null) || password.isEmpty()) {
			throw new InvalidParameterException("Password");
		}
	}

	/**
	 * Check the password of the user trying to authenticate against the ont
	 * stored
	 *
	 * @param emailAddress
	 * @param password
	 * @return
	 * @throws AuthenticateUserException
	 */
	public User authenticateUserAgainstCredentials(final UserDTO userDto)
			throws AuthenticateUserException {

		if (this.user != null) {
			throw new AuthenticateUserException("User already authenticated.");
		}

		final User userRetreive = this.dao.getUserFromCredentials(userDto
				.getEmailAddress());

		if ((userRetreive != null)
				&& userRetreive.getPassword().equals(userDto.getPassword())) {
			this.user = userRetreive;
		} else {
			throw new AuthenticateUserException(
					"User cannot be authenticated. Credentials are invalid.");
		}

		return (userRetreive);

	}

	/**
	 * This method creates a user from fullname,email and password if it doesn't
	 * already exists.
	 *
	 * @param fullName
	 * @param emailAddress
	 * @param password
	 * @return id : The unic id representing the database key.
	 * @throws InvalidParameterException
	 *             : if at least one parameter is either null or empty
	 * @throws UserAlreadyExistException
	 *             : if the email is already used by an another User
	 * @throws CannotCreateExceptionException
	 */
	public String createUser(final String fullName, final String emailAddress,
			final String password) throws InvalidParameterException,
			CannotCreateExceptionException {

		if (this.user != null) {
			throw new CannotCreateExceptionException(
					"User already connected and identify.");
		}

		this.checkUserValueAreValid(fullName, emailAddress, password);

		if (this.dao.isEmailAlreadyExisits(emailAddress)) {
			throw new CannotCreateExceptionException(
					"User already exists with email address " + emailAddress);
		}

		this.user = new User(fullName, emailAddress, password);
		this.user.setId(this.dao.storeNewUser(this.user));

		return this.getUser().getId();
	}

	public void updateUser(final String fullName, final String emailAdress,
			final String password) throws UpdateUserException,
			InvalidParameterException {

		if (this.user == null) {
			throw new UpdateUserException(
					"User not identified are not allowed to update User.");
		}

		this.checkUserValueAreValid(fullName, emailAdress, password);

		if (!emailAdress.equals(this.user.getEmailAddress())) {
			throw new UpdateUserException(
					"User can't modify information about other User.");
		}

		final User retreiveUser = this.dao.getUserFromCredentials(emailAdress);

		if (retreiveUser == null) {
			throw new UpdateUserException(
					"No User found in the database with these credentials.");
		}

		this.user.setFullName(fullName);
		this.user.setPassword(password);

		this.dao.saveUser(retreiveUser);

	}
}
