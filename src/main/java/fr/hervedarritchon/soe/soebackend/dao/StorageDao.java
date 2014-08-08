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

	private final Map<String, User> userStorage;

	/**
	 * Constructor : Instanciate the storage
	 *
	 */
	public StorageDao() {
		this.userStorage = new HashMap<String, User>();
	}

	/**
	 * Store a user in the Storage
	 *
	 * @param user
	 * @return
	 */
	public String storeNewUser(final User user) {
		final String id = UUID.randomUUID().toString();
		user.setId(id);
		saveUser(user);
		return id;
	}

	/**
	 * @param user
	 */
	public void saveUser(final User user) {
		this.userStorage.put(user.getEmailAddress(), user);
	}

	public void deleteAllUser() {
		logger.info("Nombre d'élément : {}", this.userStorage.size());
		this.userStorage.clear();
	}

	public boolean isUserAlreadyExists(final User userToTest) {

		return this.userStorage.containsKey(userToTest.getEmailAddress());
	}

	public User getUserFromCredentials(final String credentials) {
		return this.userStorage.get(credentials);
	}
}
