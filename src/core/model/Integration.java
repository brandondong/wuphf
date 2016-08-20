package core.model;

public interface Integration {

	/**
	 * 
	 * @return a unique identifier for the integration within its platform (e.g.
	 *         username)
	 */
	String getId();

	/**
	 * 
	 * @return the {@link Platform} the integration belongs to
	 */
	Platform getPlatform();

	/**
	 * Posts a status message
	 * 
	 * @param message
	 *            the status to post
	 */
	void post(String message);

}
