/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import fr.hervedarritchon.soe.soebackend.UserApplication;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.UserAlreadyExistException;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserApplicationTest {

	private static final String EMAIL_ADDRESS = "jonh.doe@jdoe-inc.com";
	private static final String FULL_NAME = "Jonh Doe";
	private static final String WRONG_PASSWORD = "wrong";
	private static final String PASSWORD = "password";

	private User testUser;
	static private UserApplication application;

	@BeforeClass
	public static void init() {
		application = new UserApplication();
	}

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
		application.getDao().deleteAllUser();
	}

	@Test
	public void should_return_authenticated_when_a_valid_authentication()
			throws UserAlreadyExistException, InvalidParameterException {
		testUser = new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD);
		application.createUser(testUser);

		assertTrue("The user should be authenticated",
				application.authenticateUserAgainstCredentials(testUser,
						PASSWORD));
	}

	@Test
	public void should_return_not_authenticated_when_an_ivalid_authentication()
			throws UserAlreadyExistException, InvalidParameterException {
		testUser = new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD);
		application.createUser(testUser);

		assertFalse("The user should be authenticated",
				application.authenticateUserAgainstCredentials(testUser,
						WRONG_PASSWORD));
	}

	@Test
	public void should_create_user_when_all_the_user_data_are_available()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User("Donald Duck", "donald.duck@acme.com",
				"QuoiDe9");
		String userId = application.createUser(newUser);

		assertFalse("New user are not authenticated", newUser.isAuthenticated());
		assertNotNull("New user has an id not null", newUser.getId());
		assertEquals("New user is created and its id returned.", userId,
				newUser.getId());
	}

	@Test(expected = UserAlreadyExistException.class)
	public void should_throw_exception_when_trying_to_create_existing_user()
			throws UserAlreadyExistException, InvalidParameterException {
		testUser = new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD);
		application.createUser(testUser);
		
		User newUser = new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD);
		String userId = application.createUser(newUser);

		assertFalse("New user are not authenticated", newUser.isAuthenticated());
		assertEquals("New user is created and its id returned.", userId,
				newUser.getId());

	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_null_fullname()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User(null, EMAIL_ADDRESS, PASSWORD);
		application.createUser(newUser);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_null_email_adress()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User(FULL_NAME, null, PASSWORD);
		application.createUser(newUser);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_null_password()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User(FULL_NAME, EMAIL_ADDRESS, null);
		application.createUser(newUser);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_empty_fullname()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User("", EMAIL_ADDRESS, PASSWORD);
		application.createUser(newUser);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_empty_email_adress()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User(FULL_NAME, "", PASSWORD);
		application.createUser(newUser);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_an_exception_when_creating_a_user_with_empty_password()
			throws UserAlreadyExistException, InvalidParameterException {
		User newUser = new User(FULL_NAME, EMAIL_ADDRESS, "");
		application.createUser(newUser);
	}

	@Test
	public void should_update_profile_when_user_authenticated() {

	}

	@Test
	public void should_throw_exception_when_guest_try_to_update_profile() {

	}
}
