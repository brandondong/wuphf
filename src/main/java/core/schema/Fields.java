package core.schema;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Fields {

	private final List<Field> fields;

	private Fields(List<Field> fields) {
		this.fields = fields;
		long numIds = fields.stream().filter(f -> f.isIdField()).count();
		checkArgument(numIds == 1,
				String.format("Platform fields must contain exactly one id field, %d found instead.", numIds));
	}

	public static Fields create(Field... acceptedFields) {
		return new Fields(Arrays.asList(acceptedFields));
	}

	public Field getIdField() {
		return fields.stream().filter(f -> f.isIdField()).findAny().get();
	}

	public Optional<Field> getFieldByLabel(String label) {
		return fields.stream().filter(f -> f.getLabel().equals(label)).findAny();
	}

	public Stream<Field> stream() {
		return fields.stream();
	}

}
