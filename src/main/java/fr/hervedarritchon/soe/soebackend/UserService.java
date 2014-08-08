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
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 *
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
@Service
public class UserService {

	private StorageDao dao;

	public UserService() {
		this(null);
	}

	@Autowired
	public UserService(StorageDao dao) {
		super();
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
	 * @throws InvalidParameterException 
	 */
	public User authenticateUserAgainstCredentials(final UserDTO userDto)
			throws AuthenticateUserException, InvalidParameterException {

//		if (this.user != null) {
//			throw new AuthenticateUserException("User already authenticated.");
//		}

		final User userRetreive = this.dao.getUserFromCredentials(new User(userDto));

		if ((userRetreive == null)
				|| userRetreive.getPassword().equals(userDto.getPassword())) {
			throw new AuthenticateUserException(
					"User cannot be authenticated. Credentials are invalid.");
		}

		return (userRetreive);

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
	public void updateUser(UserDTO userToUpdateDTO) throws UpdateUserException,
			InvalidParameterException {

//		if (this.user == null) {
//			throw new UpdateUserException(
//					"User not identified are not allowed to update User.");
//		}

		User userToUpdate = new User(userToUpdateDTO);
		
		if (!emailAdress.equals(this.user.getEmailAddress())) {
			throw new UpdateUserException(
					"User can't modify information about another User.");
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
	 * @param newUserDTO
	 * @return
	 * @throws InvalidParameterException
	 * @throws CannotCreateUserException
	 */
	public UserDTO createUser(UserDTO newUserDTO)
			throws InvalidParameterException, CannotCreateUserException {
//		if (this.user != null) {
//			throw new CannotCreateUserException(
//					"User already connected and identify.");
//		}

		User newUser = new User(newUserDTO);

		if (this.dao.isUserAlreadyExists(newUser)) {
			throw new CannotCreateUserException(
					"User already exists with email address "
							+ newUser.getEmailAddress());
		}

		newUser.setId(this.dao.storeNewUser(newUser));

		return userToUserDTO(newUser);
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
