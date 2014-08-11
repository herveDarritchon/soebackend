/**
 *
 */
package fr.hervedarritchon.soe.soebackend.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.hervedarritchon.soe.soebackend.dao.model.UserDao;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 *
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
@Repository
public class StorageDao {

	private static final Logger logger = LoggerFactory
			.getLogger(StorageDao.class);

	private final Map<String, UserDao> userStorage;

	/**
	 * Constructor : Instanciate the storage
	 *
	 */
	public StorageDao() {
		this.userStorage = new HashMap<String, UserDao>();
	}

	/**
	 * Store a user in the Storage
	 *
	 * @param userToStore
	 * @return
	 */
	public String storeNewUser(final User userToStore) {
		final UserDao userDaoToStore = new UserDao(userToStore);
		userDaoToStore.setId(UUID.randomUUID().toString());
		this.saveUser(userDaoToStore);
		return userDaoToStore.getId();
	}

	/**
	 * @param userToSave
	 */
	public void saveUser(final UserDao userToSave) {
		this.userStorage.put(userToSave.getEmailAddress(), userToSave);
	}

	public void deleteAllUser() {
		logger.info("Nombre d'élément : {}", this.userStorage.size());
		this.userStorage.clear();
	}

	public boolean isUserAlreadyExists(final User userToTest) {

		return this.userStorage.containsKey(userToTest.getEmailAddress());
	}

	public User getUserFromCredentials(final User userToAuthenticate) {
		final UserDao userDaoRetreived = this.userStorage
				.get(userToAuthenticate.getEmailAddress());
		return new User(userDaoRetreived.getId(),
				userDaoRetreived.getFullName(),
				userDaoRetreived.getEmailAddress(),
				userDaoRetreived.getPassword());
	}

	public User updateUser(final User userToUpdate) {
		final UserDao userDaoToUpdate = new UserDao(userToUpdate);
		this.saveUser(userDaoToUpdate);
		return this.getUserFromCredentials(userToUpdate);
	}
}
