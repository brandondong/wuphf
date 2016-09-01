package facebook.schema;

import core.schema.Field;
import core.schema.Fields;
import core.schema.InputType;

public class FacebookFields {

	public static final Field USERNAME = Field.builder("Username").id().create();

	public static final Field PASSWORD = Field.builder("Password").type(InputType.PASSWORD).create();

	private FacebookFields() {
	}

	public static Fields getFields() {
		return Fields.create(USERNAME, PASSWORD);
	}

}
