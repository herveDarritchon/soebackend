/**
 *
 */
package fr.hervedarritchon.soe.soebackend.api;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.hervedarritchon.soe.soebackend.UserService;
import fr.hervedarritchon.soe.soebackend.api.model.UserDto;
import fr.hervedarritchon.soe.soebackend.exception.CannotCreateUserException;
import fr.hervedarritchon.soe.soebackend.exception.InvalidParameterException;
import fr.hervedarritchon.soe.soebackend.model.User;

/**
 * @author ahdi7503
 *
 */
@Path("/users")
@Component
public class UserResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserResource.class);

	private final UserService userService;

	@Context
	private UriInfo uriInfo;

	@Autowired
	public UserResource(final UserService userService) {
		this.userService = userService;
		LOGGER.info("UserResource() with {} as a UserService",
				userService.toString());
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws CannotCreateUserException
	 * @throws InvalidParameterException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response signUp(final UserDto userToCreate)
			throws InvalidParameterException, CannotCreateUserException {

		final User userCreated = this.userService.createUser(userToCreate);

		final URI location = this.uriInfo.getAbsolutePathBuilder()
				.path(userCreated.getId()).build();

		return Response.created(location).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String unsubscribe(final UserDto toDeleteUser) {
		return "Erf, you want to quit !";
	}

	/**
	 *
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String signIn(final String idSignInUser) {
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
	public String updatePofile(final UserDto updateUser) {
		return "Hey, What's up !";
	}

}
