package core.schema;

/**
 * Corresponds to html input types
 */
public enum InputType {
	TEXT, PASSWORD, EMAIL;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
