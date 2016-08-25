package core.schema;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Fields implements Iterable<Field> {

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

	public int size() {
		return fields.size();
	}

	@Override
	public Iterator<Field> iterator() {
		return fields.iterator();
	}

}
