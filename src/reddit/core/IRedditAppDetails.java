package reddit.core;

interface IRedditAppDetails {

	/**
	 * @return the app client id
	 */
	String getClientId();

	/**
	 * @return the app client secret
	 */
	String getClientSecret();

	public static IRedditAppDetails getDefault() {
		return new RedditAppDetails();
	}

}
