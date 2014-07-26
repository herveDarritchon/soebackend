/**
 * 
 */
package fr.hervedarritchon.soe.soebackend;

import fr.hervedarritchon.soe.soebackend.dao.UserDao;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.UserAlreadyExistException;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class UserApplication {

	private UserDao dao;

	
	/**
	 * @return the dao
	 */
	public UserDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 */
	public UserApplication() {
		super();
		this.dao = new UserDao();
	}


	/**
	 * @param fullName
	 * @param emailAddress
	 * @param password
	 * @return 
	 * @throws UserAlreadyExistException 
	 * @throws InvalidParameterException 
	 */
	
	public String createUser(User userToCreate) throws UserAlreadyExistException, InvalidParameterException {
		checkUserValue(userToCreate);
		if (dao.isUserExist(userToCreate)) {
			throw new UserAlreadyExistException (userToCreate);
		}
		return dao.storeUser(userToCreate);
	}


	/**
	 * @param userToCreate
	 * @throws InvalidParameterException
	 */
	public void checkUserValue(User userToCreate)
			throws InvalidParameterException {
		if (userToCreate.getFullName()==null || userToCreate.getFullName().isEmpty())
		{
			throw new InvalidParameterException("Fullname");
		}
		if (userToCreate.getEmailAddress()==null || userToCreate.getEmailAddress().isEmpty() )
		{
			throw new InvalidParameterException("EmailAdress");
		}
		if (userToCreate.getPassword()==null || userToCreate.getPassword().isEmpty())
		{
			throw new InvalidParameterException("Password");
		}
	}
	
	public boolean authenticateUserAgainstCredentials (User user, String credentials) {
		user.setAuthenticated(credentials.equals(user.getPassword()));
		return user.isAuthenticated();
	}
	
}
