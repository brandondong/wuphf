package core.model;

import java.util.Optional;

import core.schema.FieldValueMap;

public interface Integration {

	/**
	 * 
	 * @return a unique identifier for the integration within its platform (e.g.
	 *         username)
	 */
	String getId();

	Optional<String> getLabel();

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

	void setValueMap(FieldValueMap fieldValueMap);

	FieldValueMap getFieldValueMap();

}
