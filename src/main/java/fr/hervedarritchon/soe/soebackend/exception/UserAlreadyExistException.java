/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.exception;

import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * @author throdo
 *
 */
public class UserAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642351587463236185L;

	public UserAlreadyExistException(User user) {
		super("User with email " + user.getEmailAddress() + " already exists !");
	}
}
