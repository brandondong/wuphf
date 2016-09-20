package core.model;

import core.schema.FieldValueMap;
import core.schema.Fields;

/**
 * Represents a stateless media platform on which integrations can be created
 */
public interface Platform {

	/**
	 * @return a unique label identifying the platform
	 */
	String getLabel();

	/**
	 * @return the fields that can be edited when configuring an integration
	 */
	Fields getFields();

	/**
	 * @param fieldValueMap
	 *            properties of the integration to be created
	 * @return a new {@link Integration} for this platform
	 */
	Integration createIntegration(FieldValueMap fieldValueMap);

	/**
	 * @return the filename of the platform branding logo image
	 */
	String getLogoImageLink();

}
