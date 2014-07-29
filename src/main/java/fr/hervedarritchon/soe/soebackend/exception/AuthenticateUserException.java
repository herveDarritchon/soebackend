/**
 *
 */
package fr.hervedarritchon.soe.soebackend.exception;

/**
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class AuthenticateUserException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -1991972290163332005L;

	public AuthenticateUserException(final String message) {
		super(message);
	}
}
