package web.schema;

import core.schema.Field;
import core.schema.InputType;

public class FieldWebModel {

	private String label;

	private String description;

	private boolean idField;

	private InputType type;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Field)}} to
	 * instantiate instead
	 */
	public FieldWebModel() {
	}

	private FieldWebModel(String label, String description, boolean idField, InputType type) {
		this.label = label;
		this.description = description;
		this.idField = idField;
		this.type = type;
	}

	public static FieldWebModel createFrom(Field field) {
		return new FieldWebModel(field.getLabel(), field.getDescription().orElse(null), field.isIdField(),
				field.getType());
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

	public InputType getType() {
		return type;
	}

	public void setType(InputType type) {
		this.type = type;
	}

}
