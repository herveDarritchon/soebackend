/**
 *
 */
package fr.hervedarritchon.soe.soebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hervedarritchon.soe.soebackend.api.model.UserDto;
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
	public UserService(final StorageDao dao) {
		super();
		this.dao = dao;
	}

	/**
	 * Check the password of the user trying to authenticate against the one
	 * stored
	 *
	 * @param emailAddress
	 * @param password
	 * @return
	 * @throws AuthenticateUserException
	 * @throws InvalidParameterException
	 */
	public User authenticateUserAgainstCredentials(final UserDto userDto)
			throws AuthenticateUserException, InvalidParameterException {

		final User userRetreive = this.dao.getUserFromCredentials(new User(
				userDto));

		if ((userRetreive == null)
				|| ! userRetreive.getPassword().equals(userDto.getPassword())) {
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
	public void updateUser(final UserDto userToUpdateDTO)
			throws UpdateUserException, InvalidParameterException {

		final User userToUpdate = new User(userToUpdateDTO);

		final User retreiveUser = this.dao.getUserFromCredentials(new User(
				userToUpdateDTO));

		if (retreiveUser == null) {
			throw new UpdateUserException(
					"No User found in the database with these credentials.");
		}

		this.dao.updateUser(userToUpdate);

	}

	/**
	 * Create a User
	 *
	 * @param userToCreateDTO
	 * @return
	 * @throws InvalidParameterException
	 * @throws CannotCreateUserException
	 */
	public User createUser(final UserDto userToCreateDTO)
			throws InvalidParameterException, CannotCreateUserException {

		final User userToCreate = new User(userToCreateDTO);

		if (this.dao.isUserAlreadyExists(userToCreate)) {
			throw new CannotCreateUserException(
					"User already exists with email address "
							+ userToCreate.getEmailAddress());
		}

		userToCreate.setId(this.dao.storeNewUser(userToCreate));

		return userToCreate;
	}

	/**
	 * @return the dao
	 */
	public StorageDao getDao() {
		return this.dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(final StorageDao dao) {
		this.dao = dao;
	}
}
