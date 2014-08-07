/**
 *
 */
package fr.hervedarritchon.soe.soebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hervedarritchon.soe.soebackend.api.model.UserDTO;
import fr.hervedarritchon.soe.soebackend.dao.StorageDao;
import fr.hervedarritchon.soe.soebackend.exception.AuthenticateUserException;
import fr.hervedarritchon.soe.soebackend.exception.CannotCreateUserException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.UpdateUserException;
import fr.hervedarritchon.soe.soebackend.exception.UserAlreadyExistException;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 *
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
@Service
public class UserService {

	private User user;

	private StorageDao dao;

	public UserService() {
		this(null);
	}

	@Autowired
	public UserService(StorageDao dao) {
		super();
		this.user = null;
		this.dao = dao;
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
	 * @throws CannotCreateUserException
	 */
	public String createUser(final String fullName, final String emailAddress,
			final String password) throws InvalidParameterException,
			CannotCreateUserException {

		if (this.user != null) {
			throw new CannotCreateUserException(
					"User already connected and identify.");
		}

		this.checkUserValueAreValid(fullName, emailAddress, password);

		if (this.dao.isEmailAlreadyExisits(emailAddress)) {
			throw new CannotCreateUserException(
					"User already exists with email address " + emailAddress);
		}

		this.user = new User(fullName, emailAddress, password);
		this.user.setId(this.dao.storeNewUser(this.user));

		return this.getUser().getId();
	}

	/**
	 * Update a User
	 * 
	 * @param fullName
	 * @param emailAdress
	 * @param password
	 * @throws UpdateUserException
	 * @throws InvalidParameterException
	 */
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

	/**
	 * Create a User
	 * 
	 * @param newUser
	 * @return
	 * @throws InvalidParameterException
	 * @throws CannotCreateUserException
	 */
	public UserDTO createUser(UserDTO newUser)
			throws InvalidParameterException, CannotCreateUserException {
		if (this.user != null) {
			throw new CannotCreateUserException(
					"User already connected and identify.");
		}

		this.checkUserValueAreValid(newUser.getFullName(),
				newUser.getEmailAddress(), newUser.getPassword());

		if (this.dao.isEmailAlreadyExisits(newUser.getEmailAddress())) {
			throw new CannotCreateUserException(
					"User already exists with email address "
							+ newUser.getEmailAddress());
		}

		this.user = new User(newUser.getFullName(), newUser.getEmailAddress(),
				newUser.getPassword());
		this.user.setId(this.dao.storeNewUser(this.user));

		return userToUserDTO(this.getUser());
	}

	/**
	 * Transform a User to a UserDTO
	 * 
	 * @param userToCreate
	 * @return
	 */
	private UserDTO userToUserDTO(User userToCreate) {
		return new UserDTO(userToCreate);
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the dao
	 */
	public StorageDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(StorageDao dao) {
		this.dao = dao;
	}
}