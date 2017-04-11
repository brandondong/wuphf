package core.model;

/**
 * Stores the app details needed for OAuth 2.0
 */
public interface OAuthAppDetails {

	/**
	 * @return the app client id
	 */
	String getClientId();

	/**
	 * @return the app client secret
	 */
	String getClientSecret();

}
