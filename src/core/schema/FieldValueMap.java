package core.schema;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FieldValueMap {

	private final Fields fields;

	private final Map<Field, String> valueMap = new HashMap<>();

	private FieldValueMap(Fields fields, Map<String, String> values) {
		this.fields = fields;
		checkArgument(fields.stream().count() == values.size(), "Integration properties do not match platform fields.");
		for (String label : values.keySet()) {
			Optional<Field> field = fields.getFieldByLabel(label);
			checkArgument(field.isPresent(), String.format("No matching platform field found with label %s.", label));
			valueMap.put(field.get(), values.get(label));
		}
	}

	public static FieldValueMap createWith(Fields fields, Map<String, String> values) {
		return new FieldValueMap(fields, values);
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

}
