package core.schema;

import java.util.Optional;

public class Field {

	private final String label;

	private final Optional<String> description;

	private final boolean idField;

	private Field(String label, Optional<String> description, boolean idField) {
		this.label = label;
		this.description = description;
		this.idField = idField;
	}

	public String getLabel() {
		return label;
	}

	public Optional<String> getDescription() {
		return description;
	}

	public boolean isIdField() {
		return idField;
	}

	public static Builder builder(String label) {
		return new Builder(label);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	public static class Builder {

		private final String label;

		private Optional<String> description = Optional.empty();

		private boolean idField = false;

		private Builder(String label) {
			this.label = label;
		}

		public Builder description(String description) {
			this.description = Optional.of(description);
			return this;
		}

		public Builder id() {
			idField = true;
			return this;
		}

		public Field create() {
			return new Field(label, description, idField);
		}
	}

}
