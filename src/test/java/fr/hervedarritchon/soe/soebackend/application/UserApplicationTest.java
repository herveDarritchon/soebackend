/**
 *
 */
package fr.hervedarritchon.soe.soebackend.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.hervedarritchon.soe.soebackend.UserApplication;
import fr.hervedarritchon.soe.soebackend.dao.StorageDao;
import fr.hervedarritchon.soe.soebackend.exception.AuthenticateUserException;
import fr.hervedarritchon.soe.soebackend.exception.CannotCreateExceptionException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.exception.UpdateUserException;
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
	private static final User GUEST = null;

	@Mock
	private User mockedUser;

	@Mock
	private StorageDao mockedDao;

	@InjectMocks
	private UserApplication application;

	@BeforeClass
	public static void init() {
	}

	@Before
	public void setup() {
		this.application = new UserApplication(this.mockedDao, GUEST);
	}

	@After
	public void tearDown() {
		// application.getDao().deleteAllUser();
	}

	@Test
	public void should_create_a_user_when_guest_user_try_to_create_user()
			throws InvalidParameterException, CannotCreateExceptionException {

		Mockito.when(this.mockedDao.isEmailAlreadyExisits(Mockito.anyString()))
		.thenReturn(false);

		final String generatedId = UUID.randomUUID().toString();
		Mockito.when(this.mockedDao.storeNewUser(Mockito.any(User.class)))
		.thenReturn(generatedId);

		final String id = this.application.createUser(FULL_NAME, EMAIL_ADDRESS,
				PASSWORD);

		assertNotNull("Id not null", id);
		assertFalse("Id not empty", id.isEmpty());
		assertEquals("Id returned is equal to the id generated", generatedId,
				id);

		Mockito.verify(this.mockedDao).storeNewUser(Mockito.any(User.class));
		Mockito.verify(this.mockedDao).isEmailAlreadyExisits(
				Mockito.anyString());

	}

	@Test(expected = CannotCreateExceptionException.class)
	public void should_throw_exception_when_guest_trying_to_create_user_that_already_exists()
			throws CannotCreateExceptionException, InvalidParameterException {

		Mockito.when(this.mockedDao.isEmailAlreadyExisits(Mockito.anyString()))
				.thenReturn(true);

		this.application.createUser(FULL_NAME, EMAIL_ADDRESS, PASSWORD);

	}

	@Test(expected = CannotCreateExceptionException.class)
	public void should_throw_exception_when_existing_identified_user_trying_to_create_user()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.setUser(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		this.application.createUser(FULL_NAME, EMAIL_ADDRESS, PASSWORD);

	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_null_fullname()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser(null, EMAIL_ADDRESS, PASSWORD);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_null_email_adress()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser(FULL_NAME, null, PASSWORD);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_null_password()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser(FULL_NAME, EMAIL_ADDRESS, null);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_empty_fullname()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser("", EMAIL_ADDRESS, PASSWORD);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_empty_email_adress()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser(FULL_NAME, "", PASSWORD);
	}

	@Test(expected = InvalidParameterException.class)
	public void should_throw_an_exception_when_creating_a_user_with_empty_password()
			throws CannotCreateExceptionException, InvalidParameterException {

		this.application.createUser(FULL_NAME, EMAIL_ADDRESS, "");
	}

	@Test(expected = AuthenticateUserException.class)
	public void should_throw_exception_when_user_authenticated_try_to_authenticate()
			throws AuthenticateUserException {

		this.application.setUser(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		this.application.authenticateUserAgainstCredentials(EMAIL_ADDRESS,
				PASSWORD);

	}

	@Test(expected = AuthenticateUserException.class)
	public void should_throw_exception_when_user_authenticates_with_bad_authentication()
			throws AuthenticateUserException {

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		this.application.authenticateUserAgainstCredentials(EMAIL_ADDRESS,
				WRONG_PASSWORD);

	}

	@Test(expected = AuthenticateUserException.class)
	public void should_throw_exception_when_user_send_bed_credentials()
			throws AuthenticateUserException {

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(null);

		this.application.authenticateUserAgainstCredentials(EMAIL_ADDRESS,
				PASSWORD);

	}

	@Test
	public void should_authenticate_user_when_credentials_are_correct()
			throws AuthenticateUserException {

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		assertTrue("User is correctly authenticated",
				this.application.authenticateUserAgainstCredentials(
						EMAIL_ADDRESS, PASSWORD));
		assertTrue("User is now authenticated and available",
				this.application.getUser() != null);
		assertEquals("User fullname is " + FULL_NAME, FULL_NAME,
				this.application.getUser().getFullName());

		Mockito.verify(this.mockedDao, Mockito.times(1))
		.getUserFromCredentials(Mockito.anyString());
	}

	@Test
	public void should_update_user_data_when_user_gives_all_parameters()
			throws UpdateUserException, InvalidParameterException {

		this.application.setUser(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		this.application.updateUser("Leonard Cohen", EMAIL_ADDRESS,
				"Partisan123");

		assertEquals("User fullname is now Leonard Cohen", "Leonard Cohen",
				this.application.getUser().getFullName());
		assertEquals("User password is now Partisan123", "Partisan123",
				this.application.getUser().getPassword());

		Mockito.verify(this.mockedDao, Mockito.times(1)).saveUser(
				Mockito.any(User.class));

	}

	@Test(expected = UpdateUserException.class)
	public void should_throw_exception_when_guest_try_to_update_profile()
			throws UpdateUserException, InvalidParameterException {

		this.application.updateUser("Leonard Cohen", EMAIL_ADDRESS,
				"Partisan123");

	}

	@Test(expected = UpdateUserException.class)
	public void should_throw_exception_when_user_try_to_update_another_user()
			throws UpdateUserException, InvalidParameterException {

		this.application.setUser(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		this.application.updateUser("Leonard Cohen", "another.user@mail.com",
				"Partisan123");

	}

	@Test(expected = UpdateUserException.class)
	public void should_throw_exception_when_user_gives_bad_credentials()
			throws UpdateUserException, InvalidParameterException {

		this.application.setUser(new User(FULL_NAME, EMAIL_ADDRESS, PASSWORD));

		Mockito.when(this.mockedDao.getUserFromCredentials(Mockito.anyString()))
		.thenReturn(null);

		this.application.updateUser("Leonard Cohen", EMAIL_ADDRESS,
				"Partisan123");

	}

}
