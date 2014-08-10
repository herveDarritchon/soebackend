package fr.hervedarritchon.soe.soebackend;

import fr.hervedarritchon.soe.soebackend.api.model.UserDto;
import fr.hervedarritchon.soe.soebackend.exception.AuthenticateUserException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidUserDtoException;
import fr.hervedarritchon.soe.soebackend.model.SoeSession;
import fr.hervedarritchon.soe.soebackend.model.User;

public class SessionManager {

	private SoeSession session;

	private UserService userManager;

	/**
	 * @return the session
	 */
	public SoeSession getSession() {
		return this.session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(final SoeSession session) {
		this.session = session;
	}

	/**
	 * @return the userManager
	 */
	public UserService getUserManager() {
		return this.userManager;
	}

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(final UserService userManager) {
		this.userManager = userManager;
	}

	/**
	 *
	 */
	public SessionManager() {
		super();
	}

	/* **************************** BUSINESS *************************** */

	public SoeSession createSession(final UserDto userDto)
			throws AuthenticateUserException, InvalidUserDtoException,
			InvalidParameterException {

		if (userDto == null) {
			throw new InvalidUserDtoException("UserDTO should not be null.");
		}

		if (this.session == null) {
			final User user = this.userManager
					.authenticateUserAgainstCredentials(userDto);
			if (user != null) {
				this.session = new SoeSession(user);
			} else {
				throw new InvalidUserDtoException("UserDTO unknown.");
			}
		}
		return this.session;
	}

	public boolean deleteSession(final SoeSession currentSession) {
		if (currentSession.equals(this.session)) {
			this.session = null;
		}
		return (this.session == null);
	}

	public SoeSession retreiveSession() {
		return this.session;
	}

	public SoeSession updateSession(final SoeSession currentSession) {
		return null;
	}

}
