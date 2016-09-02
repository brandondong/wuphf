package core.model;

import java.util.Optional;

import core.schema.FieldValueMap;

public interface Integration {

	/**
	 * @return a unique identifier for the integration within its platform (e.g.
	 *         username, email)
	 */
	String getId();

	/**
	 * @return an optional label to display the integration with
	 */
	Optional<String> getLabel();

	/**
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

	/**
	 * Updates the {@link FieldValueMap}
	 * 
	 * @param fieldValueMap
	 *            the new map to use
	 */
	void setValueMap(FieldValueMap fieldValueMap);

	/**
	 * @return this integration's {@link FieldValueMap}
	 */
	FieldValueMap getFieldValueMap();

}
