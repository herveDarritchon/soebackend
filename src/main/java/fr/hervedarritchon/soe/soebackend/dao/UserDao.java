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
public class UserDao {

	private Map<String, User> storage;

	/**
	 * Constructor :
	 * Instanciate the storage
	 * 
	 */
	public UserDao() {
		storage = new HashMap<String, User>();
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
		storage.put(id, user);
		return id;
	}
	
	public void deleteAllUser() {
		storage.clear();
	}

	public boolean isUserExist(User userToCreate) {
		boolean isUnic=false;
		
		for(Entry<String, User> entry : storage.entrySet()) {
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
