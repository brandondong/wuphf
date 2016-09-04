package core.model;

import core.schema.FieldValueMap;

public abstract class AbstractIntegration implements Integration {

	private final String label;

	private final FieldValueMap fieldValueMap;

	public AbstractIntegration(String label, FieldValueMap fieldValueMap) {
		this.label = label;
		this.fieldValueMap = fieldValueMap;
	}

	protected String getLabel() {
		return label;
	}

	protected FieldValueMap getFieldValueMap() {
		return fieldValueMap;
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
