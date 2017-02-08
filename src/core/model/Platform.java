package core.model;

import java.util.concurrent.CompletableFuture;

import core.schema.FieldValueMap;
import core.schema.Fields;

/**
 * Represents a stateless media platform on which integrations can be created
 */
public interface Platform {

	// TODO change this once hosted
	public static final String APP_REDIRECT = "http://localhost:8080/wuphf/oauth.html";

	/**
	 * @return a unique label identifying the platform
	 */
	String getLabel();

	/**
	 * @return the fields that can be edited when configuring an integration
	 */
	Fields getUserFields();

	/**
	 * @return the fields that can be edited when configuring another
	 *         integration
	 */
	Fields getReceiverFields();

	/**
	 * @param fieldValueMap
	 *            properties of the integration to be created
	 * @return a new {@link Integration} for this platform
	 */
	Integration createIntegration(FieldValueMap fieldValueMap);

	/**
	 * 
	 * @param code
	 *            code parameter returned from the redirect login
	 * @return a {@link FieldValueMap} holding the properties for a new
	 *         integration
	 */
	CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code);

	/**
	 * @return the filename of the platform branding logo image
	 */
	String getLogoImageLink();

	/**
	 * 
	 * @return the url to login to this platform which will redirect back once
	 *         authenticated
	 */
	String getLoginRedirectUrl();

}
