package web.schema;

import core.schema.Field;

public class FieldWebModel {

	private String label;

	private String description;

	private boolean idField;

	private boolean secret;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Field)}} to
	 * instantiate instead
	 */
	public FieldWebModel() {
	}

	private FieldWebModel(String label, String description, boolean idField, boolean secret) {
		this.label = label;
		this.description = description;
		this.idField = idField;
		this.secret = secret;
	}

	public static FieldWebModel createFrom(Field field) {
		return new FieldWebModel(field.getLabel(), field.getDescription().orElse(null), field.isIdField(),
				field.isSecret());
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

	public boolean isIdField() {
		return idField;
	}

	public void setIdField(boolean idField) {
		this.idField = idField;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

}
