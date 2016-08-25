package core.model;

import java.util.Optional;

import core.schema.FieldValueMap;
import core.schema.Fields;

/**
 * Represents a stateless media platform on which integrations can be created
 */
public interface Platform {

	/**
	 * 
	 * @return a unique label identifying the platform
	 */
	String getLabel();

	/**
	 * 
	 * @return the fields that can be edited when configuring an integration
	 */
	Fields getFields();

	Integration createIntegration(Optional<String> label, FieldValueMap fieldValueMap);

}
