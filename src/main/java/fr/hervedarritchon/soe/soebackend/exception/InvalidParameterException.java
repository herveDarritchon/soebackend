/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.exception;


/**
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class InvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3697803867619724864L;

	public InvalidParameterException(String field) {
		super("Invalid parameter - " + field + " cannot be null.");
	}

}
