/**
 *
 */
package fr.hervedarritchon.soe.soebackend;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.hervedarritchon.soe.soebackend.api.model.UserDto;
import fr.hervedarritchon.soe.soebackend.exception.AuthenticateUserException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidUserDtoException;
import fr.hervedarritchon.soe.soebackend.model.SoeSession;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SessionManagerTest {

	private static final String EMAIL_ADDRESS = "jonh.doe@jdoe-inc.com";
	private static final String FULL_NAME = "Jonh Doe";
	private static final String WRONG_PASSWORD = "wrong";
	private static final String PASSWORD = "password";
	private static final UserDto GUEST = null;
	private static final String USER_ID = UUID.randomUUID().toString();

	@Mock
	private UserService mockedUserManager;

	@InjectMocks
	private SessionManager session;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.session = new SessionManager();
		this.session.setUserManager(this.mockedUserManager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = InvalidUserDtoException.class)
	public void should_not_create_session_when_trying_to_create_session_from_unknown_user()
			throws AuthenticateUserException, InvalidUserDtoException,
			InvalidParameterException {

		Mockito.when(
				this.mockedUserManager
						.authenticateUserAgainstCredentials(Mockito
								.any(UserDto.class))).thenReturn(null);

		final SoeSession session = this.session.createSession(new UserDto(
				FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.verify(this.mockedUserManager, Mockito.times(1))
				.authenticateUserAgainstCredentials(Mockito.any(UserDto.class));
		assertTrue("Session should be null.", session == null);
	}

	@Test
	public void should_create_session_from_userdto()
			throws AuthenticateUserException, InvalidUserDtoException,
			InvalidParameterException {

		Mockito.when(
				this.mockedUserManager
						.authenticateUserAgainstCredentials(Mockito
								.any(UserDto.class))).thenReturn(
				new User(USER_ID, FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		final SoeSession session = this.session.createSession(new UserDto(
				FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.verify(this.mockedUserManager, Mockito.times(1))
				.authenticateUserAgainstCredentials(Mockito.any(UserDto.class));
		assertTrue("Session should be not null.", session != null);
	}

	@Test(expected = InvalidUserDtoException.class)
	public void should_throw_exception_from_guest()
			throws AuthenticateUserException, InvalidUserDtoException,
			InvalidParameterException {
		this.session.createSession(null);
	}

	@Test
	public void should_return_former_session_from_already_loggedin_user()
			throws AuthenticateUserException, InvalidUserDtoException,
			InvalidParameterException {

		final SoeSession initialSession = new SoeSession();
		this.session.setSession(initialSession);

		final SoeSession finalSession = this.session.createSession(new UserDto(
				FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.verify(this.mockedUserManager, Mockito.never())
				.authenticateUserAgainstCredentials(Mockito.any(UserDto.class));
		assertTrue("New session is the same as the former one.",
				initialSession == finalSession);
	}
}
