/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.hervedarritchon.soe.soebackend.api.model.UserDTO;
import fr.hervedarritchon.soe.soebackend.api.model.Welcome;

/**
 * @author ahdi7503
 *
 */
@Path ("/users")
public class UserResource {

	 private static final Logger LOGGER = LoggerFactory
				.getLogger(UserResource.class);
	 
	  public UserResource() {
	        LOGGER.info("UserResource()");
	    }
	  
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Welcome signUp(UserDTO newUser) {
		Welcome welcome = new Welcome();
		welcome.setDomain("fr.hervedarritchon.soe");
		String message = "Hey, You want to become a member " +  newUser.getFullname();
		welcome.setMessage(message);
		return welcome;
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String unsubscribe(UserDTO toDeleteUser) {
		return "Erf, you want to quit !";
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String signIn(String idSignInUser) {
		return "Hey, Welcome back !";
	}
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePofile(UserDTO updateUser) {
		return "Hey, What's up !";
	}
	
}