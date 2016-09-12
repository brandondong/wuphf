package core.schema;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FieldValueMap {

	private final Fields fields;

	private final Map<Field, String> valueMap;

	private FieldValueMap(Fields fields, Map<Field, String> valueMap) {
		this.fields = fields;
		this.valueMap = valueMap;
		fields.stream().forEach(f -> checkState(valueMap.containsKey(f),
				String.format("Value must be set for %s field.", f.getLabel())));
	}

	public String getValueForField(Field field) {
		checkArgument(valueMap.containsKey(field),
				String.format("Field %s not found in platform fields.", field.getLabel()));
		return valueMap.get(field);
	}

	public String getIdValue() {
		return getValueForField(fields.getIdField());
	}

	public Fields getFields() {
		return fields;
	}

	public static Builder builder(Fields fields) {
		return new Builder(fields);
	}

	public static class Builder {

		private final Fields fields;

		private final Map<Field, String> valueMap = new HashMap<>();

		private Builder(Fields fields) {
			this.fields = fields;
		}

		public Builder setField(String label, String value) {
			Optional<Field> field = fields.getFieldByLabel(label);
			checkArgument(field.isPresent(), String.format("No matching platform field found with label %s.", label));
			checkArgument(!value.isEmpty(), String.format("Value for %s field cannot be empty.", label));
			valueMap.put(field.get(), value);
			return this;
		}

		public FieldValueMap create() {
			return new FieldValueMap(fields, valueMap);
		}
	}

}
