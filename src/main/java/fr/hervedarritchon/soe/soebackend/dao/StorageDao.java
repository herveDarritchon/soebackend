/**
 *
 */
package fr.hervedarritchon.soe.soebackend.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.hervedarritchon.soe.soebackend.model.User;

/**
 *
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
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
	public String storeUser(final User user) {
		final String id = UUID.randomUUID().toString();
		user.setId(id);
		this.userStorage.put(id, user);
		return id;
	}

	public void deleteAllUser() {
		logger.info("Nombre d'élément : {}", this.userStorage.size());
		this.userStorage.clear();
	}

	public boolean isUserExist(final User userToCreate) {
		boolean isUnic = false;

		for (final Entry<String, User> entry : this.userStorage.entrySet()) {
			// String key = entry.getKey();
			final User value = entry.getValue();
			if (value.getEmailAddress().equals(userToCreate.getEmailAddress())) {
				isUnic = true;
				break;
			}
		}
		return isUnic;
	}

}
