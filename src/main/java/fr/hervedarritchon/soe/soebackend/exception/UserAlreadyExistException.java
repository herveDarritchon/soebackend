/**
 *
 */
package fr.hervedarritchon.soe.soebackend.exception;


/**
 * @author throdo
 *
 */
public class UserAlreadyExistException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -3642351587463236185L;

	public UserAlreadyExistException(final String emailAddress) {
		super("User with email " + emailAddress + " already exists !");
	}
}
