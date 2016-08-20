package core.model;

public interface Integration {

	/**
	 * 
	 * @return a unique identifier for the integration within its platform (e.g.
	 *         username)
	 */
	String getId();

	Platform getPlatform();

}
