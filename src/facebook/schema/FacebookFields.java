package facebook.schema;

import core.schema.Field;
import core.schema.Fields;

public class FacebookFields {

	public static final Field USERNAME = Field.builder("Username").id().create();

	private FacebookFields() {
	}

	public static Fields getFields() {
		return Fields.create(USERNAME);
	}

}
