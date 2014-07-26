/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * 
 * @author Hervé Darritchon (@hervDarritchon)
 *
 */
public class StorageDao {

	private Map<String, User> userStorage;

	/**
	 * Constructor :
	 * Instanciate the storage
	 * 
	 */
	public StorageDao() {
		userStorage = new HashMap<String, User>();
	}

	/**
	 * Store a user in the Storage
	 * 
	 * @param user
	 * @return
	 */
	public String storeUser(User user) {
		final String id = UUID.randomUUID().toString();
		user.setId(id);
		userStorage.put(id, user);
		return id;
	}
	
	public void deleteAllUser() {
		userStorage.clear();
	}

	public boolean isUserExist(User userToCreate) {
		boolean isUnic=false;
		
		for(Entry<String, User> entry : userStorage.entrySet()) {
		    //String key = entry.getKey();
		    User value = entry.getValue();
		    if (value.getEmailAddress().equals(userToCreate.getEmailAddress())){
		    	isUnic= true;
		    	break;
		    }
		}
		return isUnic;
	}
	
}
