/**
 *
 */
package fr.hervedarritchon.soe.soebackend.exception;

/**
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class InvalidUserDtoException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -5407376964053927199L;

	public InvalidUserDtoException(final String message) {
		super(message);
	}
}
