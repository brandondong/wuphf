package core.model;

import core.schema.Fields;

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

}
