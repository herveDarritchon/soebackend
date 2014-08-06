/**
 * 
 */
package fr.hervedarritchon.soe.soebackend.api;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author ahdi7503
 *
 */
public class SoeRestApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public SoeRestApplication() {
		//register(RequestContextFilter.class);
		register(UserResource.class);
//		register(SpringSingletonResource.class);
//		register(SpringRequestResource.class);
//		register(CustomExceptionMapper.class);
	}
}