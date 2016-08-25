package web.schema;

import core.schema.Field;

public class FieldWebModel {

	private String label;

	private String description;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Field)}} to
	 * instantiate instead
	 */
	public FieldWebModel() {
	}

	private FieldWebModel(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public static FieldWebModel createFrom(Field field) {
		return new FieldWebModel(field.getLabel(), field.getDescription().orElse(null));
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
