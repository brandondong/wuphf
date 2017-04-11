package facebook.core;

import core.schema.Field;
import core.schema.Fields;
import core.schema.InputType;

class FacebookFields {

	public static final Field NAME = Field.builder("Name").id().create();

	public static final Field EMAIL = Field.builder("Email").id().type(InputType.EMAIL).create();

	public static final Field PASSWORD = Field.builder("Password").type(InputType.PASSWORD).create();

	public static final Field ACCESS_TOKEN = Field.builder("Access token").create();

	public static final Field EXPIRES_AT = Field.builder("Expires at").create();

	private FacebookFields() {
	}

	public static Fields getUserFields() {
		return Fields.create(NAME, ACCESS_TOKEN, EXPIRES_AT);
	}

	public static Fields getReceiverFields() {
		return Fields.create(EMAIL);
	}

}
